package com.example.expensecontrol.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CategoryDto {
    @NotNull(message = "A categoria não pode ser nula.")
    @NotBlank(message = "A categoria não pode ser vazia.")
    private String name;

}
