package com.example.expensecontrol.controler;

import java.util.List;
import com.example.expensecontrol.entities.Category;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.expensecontrol.dtos.CategoryDto;
import com.example.expensecontrol.service.CategoryService;
import com.example.expensecontrol.utils.modelmapper.CategoryMapper;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/findall")
    public ResponseEntity<List<CategoryDto>> findall(){
        List<Category> list = categoryService.findall();
        List<CategoryDto> listDto = CategoryMapper.listCategoryToDto(list);

        return ResponseEntity.ok(listDto);
    }

    @PostMapping("/save")
    public ResponseEntity<CategoryDto> save(@RequestBody CategoryDto dto){
        Category category = CategoryMapper.toCategory(dto);
        categoryService.save(category);

        return ResponseEntity.ok(CategoryMapper.toDto(category));
    }

}
