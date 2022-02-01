package com.gomes.dataregister.finance.repository;

import com.gomes.dataregister.finance.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BudgetRepository extends JpaRepository<Budget, Integer> {
}
