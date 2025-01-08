package com.example.expensecontrol.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;

import com.example.expensecontrol.entities.Category;
import com.example.expensecontrol.repository.CategoryRepository;

public class CategoryServiceTest {
    @Mock
    private CategoryRepository categoryRepository;
    @InjectMocks
    private CategoryService categoryService;

    public CategoryServiceTest(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save_ShouldThrowException_WhenCategoryAlreadyExists(){
        // arrange
        String categoryName = "viagem";
        Category category = new Category();
        category.setName(categoryName);

        when(categoryRepository.existsByName(categoryName)).thenReturn(true);
        // act e asserts
        DataIntegrityViolationException exception = assertThrows(
            DataIntegrityViolationException.class,
            ()-> categoryService.save(category)
        );
        assertEquals("A categoria já está cadastrada",exception.getMessage());
        verify(categoryRepository, never()).save(any(Category.class));
    }

    @Test
    void save_ShouldSaveCategoryWhenCategoryDoesNotExists(){
        //arrange
        String categoryName = "technology";
        Category category = new Category();
        category.setName(categoryName);

        when(categoryRepository.existsByName(categoryName)).thenReturn(false);
        when(categoryRepository.save(category)).thenReturn(category);
        //act
        Category savedCategory = categoryService.save(category);
        // assert
        assertNotNull(savedCategory);
        assertEquals(categoryName, savedCategory.getName());
        verify(categoryRepository, times(1)).save(category);

    }

    @Test
    void testFindAll(){
        Category category1 = new Category();
        category1.setName("categoria1");
        Category category2 = new Category();
        category2.setName("categoria2");
       

        when(categoryRepository.findAll()).thenReturn(List.of(category1, category2)); // return the list with the two objects created

        // executing the method service
        List<Category> categories = categoryService.findall();
        assertNotNull(categories);// not null
        assertEquals(2,categories.size());// size of the list two
        assertEquals("categoria1", categories.get(0).getName());// verify if are the same names
        assertEquals("categoria2", categories.get(1).getName());// verify if are the same names
        verify(categoryRepository, times(1)).findAll();// verify if the repository was ordered one time
    }

}
