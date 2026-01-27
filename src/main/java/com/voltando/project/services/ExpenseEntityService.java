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


@Service
public class ExpenseEntityService {

    private final ExpenseEntityRepository expenseEntityRepository;
    private final UserEntityRepository userEntityRepository;

    public ExpenseEntityService(ExpenseEntityRepository expenseEntityRepository, UserEntityRepository userEntityRepository) {
        this.expenseEntityRepository = expenseEntityRepository;
        this.userEntityRepository = userEntityRepository;
    }

    private UserEntity getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();

        return userEntityRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("Usuário não autenticado"));
    }

    public List<ListExpenseDto> listByUserId(Integer userId) {
        return expenseEntityRepository.findByUserEntityId(userId)
                .stream()
                .map(expense -> new ListExpenseDto(
                        expense.getId(),
                        expense.getDescription(),
                        expense.getValue(),
                        expense.getDate(),
                        expense.getCategory()
                ))
                .toList();
    }

    public ExpenseEntity createExpense(CreateExpenseDto dto) {

        UserEntity user = getAuthenticatedUser();

        ExpenseEntity expense = new ExpenseEntity();
        expense.setDescription(dto.getDescription());
        expense.setValue(dto.getValue());
        expense.setDate(dto.getDate());
        expense.setCategory(dto.getCategory());
        expense.setUserEntity(user);

        return expenseEntityRepository.save(expense);
    }

    public ExpenseEntity updateExpense(UpdateExpenseDto expenseEntity, Integer id) {
        ExpenseEntity updateExpense = expenseEntityRepository.findById(id).orElseThrow(() -> new RuntimeException("Despesa não encontrada"));

        updateExpense.setCategory(expenseEntity.getCategory());
        updateExpense.setDate(expenseEntity.getDate());
        updateExpense.setDescription(expenseEntity.getDescription());
        updateExpense.setValue(expenseEntity.getValue());


        return expenseEntityRepository.save(updateExpense);
    }

    public void deleteExpense(Integer id) {

        if (!expenseEntityRepository.existsById(id)) {
            throw new RuntimeException("Despesa não encontrada");
        }

        expenseEntityRepository.deleteById(id);
    }

}
