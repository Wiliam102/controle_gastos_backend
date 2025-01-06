package com.example.expensecontrol.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.expensecontrol.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
  boolean existsByName(String name);

}
