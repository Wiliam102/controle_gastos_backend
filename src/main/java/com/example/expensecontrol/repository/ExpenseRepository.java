package com.example.expensecontrol.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.expensecontrol.entities.Expense;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense,Long> {
    boolean existsByDescriptionAndDate(String description, LocalDate date);

}
