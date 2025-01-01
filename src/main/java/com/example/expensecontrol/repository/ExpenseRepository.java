package com.example.expensecontrol.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.expensecontrol.entities.Expense;

public interface ExpenseRepository extends JpaRepository<Long,Expense> {

}
