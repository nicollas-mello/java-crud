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
@RequestMapping("/api/expenses")
public class ExpenseEntityController {

    private final ExpenseEntityService expenseService;

    public ExpenseEntityController(ExpenseEntityService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping
    public List<ListExpenseDto> listUserExpenses() {
        return expenseService.listUserExpenses();
    }

    @PostMapping
    public ResponseEntity<ExpenseEntity> createExpense(
            @RequestBody CreateExpenseDto dto
    ) {
        ExpenseEntity expense = expenseService.createExpense(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(expense);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ExpenseEntity> updateExpense(
            @PathVariable Integer id,
            @RequestBody UpdateExpenseDto dto
    ) {
        ExpenseEntity updated = expenseService.updateExpense(dto, id);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteExpense(@PathVariable Integer id) {
        expenseService.deleteExpense(id);
    }
}

