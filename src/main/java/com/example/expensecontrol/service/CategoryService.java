package com.example.expensecontrol.service;

import com.example.expensecontrol.repository.CategoryRepository;
import com.example.expensecontrol.entities.Category;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<Category> findall(){
        return categoryRepository.findAll();
    }

    public Category save(Category category){
        if(categoryRepository.existsByName(category.getName())){
            throw new DataIntegrityViolationException("A categoria já está cadastrada");
        }
        else{
            return categoryRepository.save(category);
        }
    }

   
    }

