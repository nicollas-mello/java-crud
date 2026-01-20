package com.voltando.project.repositories;

import com.voltando.project.entities.ExpenseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseEntityRepository extends JpaRepository<ExpenseEntity, Integer> {

    List<ExpenseEntity> findByUserEntityId(Integer userId);
}
