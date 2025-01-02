package com.example.expensecontrol.utils.modelmapper;

import org.modelmapper.ModelMapper;

import com.example.expensecontrol.dtos.ExpenseDto;
import com.example.expensecontrol.entities.Expense;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ExpenseMapper {
    public static Expense toExpense(ExpenseDto dto){
        return new ModelMapper().map(dto, Expense.class);
    }

    public static ExpenseDto toDto(Expense expense){
        return new ModelMapper().map(expense, ExpenseDto.class);
    }

}
