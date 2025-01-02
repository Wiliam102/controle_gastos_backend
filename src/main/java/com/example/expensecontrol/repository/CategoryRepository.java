package com.example.expensecontrol.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.expensecontrol.entities.Category;

public interface CategoryRepository extends JpaRepository<Category,Long> {

}
