package com.example.expensecontrol.utils.modelmapper;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

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

    //public static List<ExpenseDto> listExpenseToDto(List<Expense> listExpense, ModelMapper modelMapper){
     //   return modelMapper.map(listExpense, new TypeToken<List<ExpenseDto>>() {}.getType());
    //}

}
