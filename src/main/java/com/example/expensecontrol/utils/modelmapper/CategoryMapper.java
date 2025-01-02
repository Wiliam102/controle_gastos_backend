package com.example.expensecontrol.utils.modelmapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.Locale.Category;
import com.example.expensecontrol.dtos.CategoryDto;

import org.modelmapper.ModelMapper;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoryMapper {
    public static Category toCategory(CategoryDto dto){
        return new ModelMapper().map(dto, Category.class);
    }
    public static CategoryDto toDto(Category category){
        return new ModelMapper().map(category, CategoryDto.class);
    }

}
