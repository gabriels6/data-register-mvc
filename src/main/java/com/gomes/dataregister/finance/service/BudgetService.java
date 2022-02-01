package com.gomes.dataregister.finance.service;

import com.gomes.dataregister.finance.model.Budget;
import com.gomes.dataregister.finance.repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class BudgetService {

    @Autowired
    BudgetRepository budgetRepository;

    public Iterable<Budget> getAllBudgets() {
        return budgetRepository.findAll();
    }

    public Budget getBudgetById(int id) {
        return budgetRepository.getOne(id);
    }

    public Budget saveBudget(Budget budget) {
        return budgetRepository.save(budget);
    }

    public void deleteBudget(Budget budget) {
        budgetRepository.delete(budget);
    }

}
