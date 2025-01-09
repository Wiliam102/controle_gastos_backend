package com.example.expensecontrol.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

import com.example.expensecontrol.entities.Category;
import com.example.expensecontrol.entities.Expense;
import com.example.expensecontrol.repository.CategoryRepository;
import com.example.expensecontrol.repository.ExpenseRepository;

@ExtendWith(MockitoExtension.class)
public class ExpenseServiceTest {
    @Mock
    private ExpenseRepository expenseRepository;
    @Mock
    private CategoryRepository categoryRepository;
    @InjectMocks
    private ExpenseService expenseService;
    
    @Test
    void save_ShouldThrowException_WhenExpenselsDuplicate(){
       //
       Expense expense = new Expense();
       expense.setDescription("compras da semana");
       expense.setDate(LocalDate.now());

       when(expenseRepository.existsByDescriptionAndDate(expense.getDescription(), expense.getDate())).thenReturn(true);

       //Act e Assert

       DataIntegrityViolationException exception = assertThrows(
        DataIntegrityViolationException.class,
        ()-> expenseService.save(expense)
       );
       assertEquals("Os dados não podem ser duplicados",exception.getMessage());
       verify(expenseRepository, never()).save(any(Expense.class));
    }
    @Test
    void save_ShouldThrowException_WhenCategoryDoesNotExists(){
        //
        Expense expense = new Expense();
        Category category = new Category();
        category.setId(1L);
        expense.setCategory(category);

        when(expenseRepository.existsByDescriptionAndDate(expense.getDescription(), expense.getDate())).thenReturn(false);
        when(categoryRepository.existsById(expense.getCategory().getId())).thenReturn(false);

        // act e assert
        DataIntegrityViolationException exception = assertThrows(
        DataIntegrityViolationException.class,
        ()-> expenseService.save(expense)
       );
       assertEquals("A categoria não está cadastrada.", exception.getMessage());
       verify(expenseRepository, never()).save(any(Expense.class));

    }
    @Test
    void save_ShouldSaveExpense_WhenDateIsValid(){
        //arrange
            Expense expense = new Expense();
            Category category = new Category();
            category.setId(1L);
            expense.setDescription("compras da semana");
            expense.setDate(LocalDate.now());
            expense.setCategory(category);

            when(expenseRepository.existsByDescriptionAndDate(expense.getDescription(), expense.getDate()))
            .thenReturn(false);
            when(categoryRepository.existsById(expense.getCategory().getId())).thenReturn(true);
            when(expenseRepository.save(expense)).thenReturn(expense);
            // Act
            Expense savedExpense = expenseService.save(expense);
            // Assert
            assertNotNull(savedExpense);
            assertEquals(expense, savedExpense);
            verify(expenseRepository, times(1)).save(expense);
    }
    @Test
    void update_ShouldUpdateExpense_WhenIdExists(){
        //arrange
        Long expenseId = 1L;
        Expense existingExpense = new Expense();
        existingExpense.setId(expenseId);
        existingExpense.setDescription("description teste");

        Expense updateExpense = new Expense();
        updateExpense.setDescription("new description");

        when(expenseRepository.existsById(expenseId)).thenReturn(true);
        when(expenseRepository.save(updateExpense)).thenAnswer(invocation->{
         Expense arg = invocation.getArgument(0);
         arg.setId(expenseId);
         return arg;
        });

        Expense result = expenseService.update(updateExpense, expenseId);
        assertNotNull(result);
        assertEquals(expenseId, result.getId());
        assertEquals("new description", result.getDescription()); 
        verify(expenseRepository).save(updateExpense);       
    }
        @Test
    void testUpdateNonExistingId() {
        Long expenseId = 1L;
        Expense updatedExpense = new Expense();
        updatedExpense.setDescription("New Description");

        Mockito.when(expenseRepository.existsById(expenseId)).thenReturn(false);

        Expense result = expenseService.update(updatedExpense, expenseId);

        assertNull(result);
        Mockito.verify(expenseRepository, Mockito.never()).save(Mockito.any());
    }
    @Test
    void testDeleteSucess(){
        Long expenseId = 1L;
        doNothing().when(expenseRepository).deleteById(expenseId);
        expenseService.delete(expenseId);
        verify(expenseRepository).deleteById(expenseId);
    }

     @Test
    void testFindAll(){
        Expense expense1 = new Expense();
        expense1.setDescription("description one");
        Expense expense2 = new Expense();
        expense2.setDescription("description two");
       

        when(expenseRepository.findAll()).thenReturn(List.of(expense1, expense2)); // return the list with the two objects created

        // executing the method service
        List<Expense> expenses = expenseService.findall();
        assertNotNull(expenses);// not null
        assertEquals(2,expenses.size());// size of the list two
        assertEquals("description one", expenses.get(0).getDescription());// verify if are the same names
        assertEquals("description two", expenses.get(1).getDescription());// verify if are the same names
        verify(expenseRepository, times(1)).findAll();// verify if the repository was ordered one time
    }


}
