package com.example.expensecontrol.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.expensecontrol.entities.Expense;

public interface ExpenseRepository extends JpaRepository<Expense,Long> {
    boolean existsByDescriptionAndDate(String desription, LocalDate date);

}
