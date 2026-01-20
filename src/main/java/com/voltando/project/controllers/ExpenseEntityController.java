package com.voltando.project.controllers;

import com.voltando.project.dtos.CreateExpenseDto;
import com.voltando.project.dtos.ListExpenseDto;
import com.voltando.project.dtos.UpdateExpenseDto;
import com.voltando.project.entities.ExpenseEntity;
import com.voltando.project.services.ExpenseEntityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseEntityController {

    private final ExpenseEntityService expenseEntityService;

    public ExpenseEntityController(ExpenseEntityService expenseEntityService) {
        this.expenseEntityService = expenseEntityService;
    }

    @GetMapping("/{id}")
    public List<ListExpenseDto> listAllExpenseByUser(@PathVariable Integer id) {
        return expenseEntityService.listByUserId(id);
    }

    @PostMapping
    public ResponseEntity<ExpenseEntity> createUser(@RequestBody CreateExpenseDto expenseEntity) {
        ExpenseEntity savedExpense = expenseEntityService.createExpense(expenseEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedExpense);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpenseEntity> updateUser(@RequestBody UpdateExpenseDto expenseEntity, @PathVariable Integer id) {
        ExpenseEntity updateExpense = expenseEntityService.updateExpense(expenseEntity, id);
        return ResponseEntity.status(HttpStatus.OK).body(updateExpense);
    }

    @DeleteMapping("/{id}")
    public void deleteExpense(@PathVariable Integer id) {
        expenseEntityService.deleteExpense(id);
    }


}
