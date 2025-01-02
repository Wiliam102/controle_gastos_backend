package com.example.expensecontrol.controler;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.expensecontrol.dtos.ExpenseDto;
import com.example.expensecontrol.service.ExpenseService;
import com.example.expensecontrol.utils.modelmapper.ExpenseMapper;
import com.example.expensecontrol.entities.Expense;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequiredArgsConstructor
@RequestMapping("/expense")
public class ExpenseController {
    private final ExpenseService expenseService;

    @GetMapping("/findall")
    public ResponseEntity<List<ExpenseDto>> findall() {
        List<ExpenseDto> lista = ExpenseMapper.listExpenseToDto(expenseService.findall());
       
        return ResponseEntity.ok(lista);

    }
    @PostMapping("/save")
    public ResponseEntity<ExpenseDto> save(@RequestBody ExpenseDto expenseDto) {
        Expense expense = ExpenseMapper.toExpense(expenseDto);
        expenseService.save(expense);
        
        return ResponseEntity.ok(ExpenseMapper.toDto(expense));
    }
    @PutMapping("update/{id}")
    public ResponseEntity<ExpenseDto> update(@PathVariable Long id,@RequestBody ExpenseDto dto){

        Expense expense = ExpenseMapper.toExpense(dto);

        return ResponseEntity.ok(ExpenseMapper.toDto(expense));
      
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        expenseService.delete(id);
        return ResponseEntity.ok().build();
    }

}
