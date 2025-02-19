package com.example.expensecontrol.service;

import java.util.List;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.example.expensecontrol.entities.Expense;
import com.example.expensecontrol.repository.CategoryRepository;
import com.example.expensecontrol.repository.ExpenseRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ExpenseService {
    private final ExpenseRepository expenseRepository;//constante objeto do repository
private final CategoryRepository categoryRepository;
// objeto categoria para verificar se a categoria existe


    // method to list all expense
    public List<Expense> findall(){
        return expenseRepository.findAll();
    }

    @Transactional
    // method to save a expense
    public Expense save(Expense expense){
        // here is checking to avoid duplications
        
        if(expenseRepository.existsByDescriptionAndDate(expense.getDescription(), expense.getDate())){
            throw new DataIntegrityViolationException("Os dados não podem ser duplicados");
        }
        if(!categoryRepository.existsById(expense.getCategory().getId())){
            throw new DataIntegrityViolationException("A categoria não está cadastrada.");
        }
        else
            return expenseRepository.save(expense);
    }
    @Transactional
    // method to update a expense
    public Expense update(Expense expenseUpdate, Long id){
        // here, it checks if the id is already saved in the database 
        if(expenseRepository.existsById(id)){
            expenseUpdate.setId(id);
            return expenseRepository.save(expenseUpdate);
        }
        else
            return null;   
    }
    
    @Transactional
    public void delete(Long id){
        expenseRepository.deleteById(id);
    }

}
