package com.gomes.dataregister.finance.controller;

import com.gomes.dataregister.core.controller.BasicController;
import com.gomes.dataregister.core.service.UserService;
import com.gomes.dataregister.finance.model.Budget;
import com.gomes.dataregister.finance.service.AccountingTypeService;
import com.gomes.dataregister.finance.service.BudgetService;
import com.gomes.dataregister.finance.service.PeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/finance/budget")
public class BudgetController implements BasicController {

    @Autowired
    BudgetService budgetService;

    @Autowired
    PeriodService periodService;

    @Autowired
    AccountingTypeService accountingTypeService;

    @Autowired
    UserService userService;

    private static final String ERROR_PAGE = "core/error/index";
    private static final String INDEX_PAGE = "finance/budget/index";
    private static final String SHOW_PAGE = "finance/budget/show";
    private static final String CREATE_PAGE = "finance/budget/create";
    private static final String EDIT_PAGE = "finance/budget/edit";

    @Override
    @GetMapping(value = {"/", "", "/index"})
    public String index(Model model) {
        model.addAttribute("budgets", budgetService.getAllBudgets());
        return INDEX_PAGE;
    }

    @GetMapping(value = {"/show/{id}","/{id}"})
    public String show(@PathVariable("id") int Id, Model model) {
        model.addAttribute("budget", budgetService.getBudgetById(Id));
        return SHOW_PAGE;
    }

    @Override
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("budget", new Budget());
        model.addAttribute("accountingTypes", accountingTypeService.getAllAccountingTypes());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("periods", periodService.getAllPeriods());
        return CREATE_PAGE;
    }

    @Override
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int Id, Model model) {
        model.addAttribute("budget", budgetService.getBudgetById(Id));
        model.addAttribute("accountingTypes", accountingTypeService.getAllAccountingTypes());
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("periods", periodService.getAllPeriods());
        return EDIT_PAGE;
    }

    @Override
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int Id, Model model) {
        try {
            budgetService.deleteBudget(budgetService.getBudgetById(Id));
            model.addAttribute("messageType","success");
            model.addAttribute("message","Budget deleted successfully!");
            model.addAttribute("budgets",budgetService.getAllBudgets());
            return INDEX_PAGE;
        } catch (Exception ex) {
            model.addAttribute("messageType","error");
            model.addAttribute("message","Error delete budget: " + ex.getMessage());
            model.addAttribute("budgets", budgetService.getAllBudgets());
            return INDEX_PAGE;
        }
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("budget") Budget budget, Model model) {
        try {
            int periodId = budget.getPeriod().getId();
            int accountingTypeId = budget.getAccountingType().getId();
            int userId = budget.getUser().getId();
            budget.setPeriod(periodService.getPeriodById(periodId));
            budget.setAccountingType(accountingTypeService.getAccountingTypeById(accountingTypeId));
            budget.setUser(userService.getUserById(userId));
            budgetService.saveBudget(budget);
            model.addAttribute("messageType","success");
            model.addAttribute("message","Budget created successfully!");
            model.addAttribute("budgets",budgetService.getAllBudgets());
            return INDEX_PAGE;
        } catch (Exception ex) {
            model.addAttribute("messageType","error");
            model.addAttribute("message","Error creating budget: " + ex.getMessage());
            model.addAttribute("budgets", budgetService.getAllBudgets());
            return INDEX_PAGE;
        }
    }
}
