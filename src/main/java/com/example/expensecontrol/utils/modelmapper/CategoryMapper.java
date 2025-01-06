package com.example.expensecontrol.utils.modelmapper;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import com.example.expensecontrol.entities.Category;
import com.example.expensecontrol.dtos.CategoryDto;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CategoryMapper {
    public static Category toCategory(CategoryDto dto){
        return new ModelMapper().map(dto, Category.class);
    }
    public static CategoryDto toDto(Category category){
        return new ModelMapper().map(category, CategoryDto.class);
    }

    public static List<CategoryDto> listCategoryToDto(List<Category> list){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(list, new TypeToken<List<CategoryDto>>() {}.getType());

    }

}
