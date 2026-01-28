package com.voltando.project.services;

import com.voltando.project.dtos.CreateExpenseDto;
import com.voltando.project.dtos.ListExpenseDto;
import com.voltando.project.dtos.UpdateExpenseDto;
import com.voltando.project.entities.ExpenseEntity;
import com.voltando.project.entities.UserEntity;
import com.voltando.project.repositories.ExpenseEntityRepository;
import com.voltando.project.repositories.UserEntityRepository;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ExpenseEntityService {

    private final ExpenseEntityRepository expenseRepository;
    private final UserEntityRepository userRepository;

    public ExpenseEntityService(
            ExpenseEntityRepository expenseRepository,
            UserEntityRepository userRepository
    ) {
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
    }

    /* =====================
       Usuário autenticado
       ===================== */

    private UserEntity getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        return userRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new RuntimeException("Usuário não autenticado"));
    }

    /* =====================
       Listar despesas
       ===================== */

    public List<ListExpenseDto> listUserExpenses() {
        UserEntity user = getAuthenticatedUser();

        return expenseRepository.findByUserEntity(user)
                .stream()
                .map(this::toListDto)
                .toList();
    }

    /* =====================
       Criar despesa
       ===================== */

    public ExpenseEntity createExpense(CreateExpenseDto dto) {
        UserEntity user = getAuthenticatedUser();

        ExpenseEntity expense = new ExpenseEntity();
        expense.setDescription(dto.getDescription());
        expense.setValue(dto.getValue());
        expense.setDate(dto.getDate());
        expense.setCategory(dto.getCategory());
        expense.setUserEntity(user);

        return expenseRepository.save(expense);
    }

    public ExpenseEntity updateExpense(UpdateExpenseDto dto, Integer id) {
        UserEntity user = getAuthenticatedUser();
        ExpenseEntity expense = findById(id);

        validateOwnership(expense, user);

        expense.setDescription(dto.getDescription());
        expense.setValue(dto.getValue());
        expense.setDate(dto.getDate());
        expense.setCategory(dto.getCategory());

        return expenseRepository.save(expense);
    }

    public void deleteExpense(Integer id) {
        UserEntity user = getAuthenticatedUser();
        ExpenseEntity expense = findById(id);

        validateOwnership(expense, user);

        expenseRepository.delete(expense);
    }

    private ExpenseEntity findById(Integer id) {
        return expenseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Despesa não encontrada"));
    }


    private void validateOwnership(ExpenseEntity expense, UserEntity user) {
        if (!Objects.equals(user.getId(), expense.getUserEntity().getId())) {
            throw new RuntimeException("Acesso negado");
        }
    }

    private ListExpenseDto toListDto(ExpenseEntity expense) {
        return new ListExpenseDto(
                expense.getDescription(),
                expense.getValue(),
                expense.getDate(),
                expense.getCategory()
        );
    }
}
