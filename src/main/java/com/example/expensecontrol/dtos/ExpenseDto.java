package com.example.expensecontrol.dtos;

import java.time.LocalDate;
import java.util.Date;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class ExpenseDto {
    
    @NotBlank(message = "O valor não pode ser vazio")
    @NotNull(message = "O valor não pode ser nulo")
    @Min(value = 1, message = "O valor precisa ser maior ou igual a 1.")
    private Double amount;
    @NotBlank(message = "A data não pode ser vazia")
    @NotNull(message = "A data não pode ser nula")
    private LocalDate date;
    @NotBlank(message = "A descricao não pode ser vazia")
    @NotBlank(message = "A descricao não pode ser nula")
    private String description;
    @NotNull(message = "A cactegoria não pode ser vazia")
    @NotBlank(message = "a categoria não pode ser nula")
    private Long categoryId;
}
