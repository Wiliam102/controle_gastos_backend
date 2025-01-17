package com.example.expensecontrol.dtos;

import java.time.LocalDate;

import com.example.expensecontrol.entities.Category;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class ExpenseDto {
    
    @NotNull(message = "O valor não pode ser nulo")
    @DecimalMin(value = "1.0", message = "O valor precisa ser maior ou igual a 1.")
    private Double amount;
    @PastOrPresent(message = "A data não pode ser no futuro")
    @NotNull(message = "A data não pode ser nula")
    private LocalDate date;
    @NotBlank(message = "A descricao não pode ser nula")
    private String description;
    @NotNull(message = "A categoria não pode ser nula")
    private Category category;


    public ExpenseDto(){}
}
