package com.gomes.dataregister.finance.controller;

import com.gomes.dataregister.core.controller.BasicController;
import com.gomes.dataregister.core.response.ErrorResponse;
import com.gomes.dataregister.core.service.SessionService;
import com.gomes.dataregister.core.service.UserService;
import com.gomes.dataregister.finance.model.Accounting;
import com.gomes.dataregister.finance.service.AccountingService;
import com.gomes.dataregister.finance.service.AccountingTypeService;
import com.gomes.dataregister.finance.service.PeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@Controller
@RequestMapping("/finance/accounting")
public class AccountingController implements BasicController {

    @Autowired
    AccountingService accountingService;

    @Autowired
    AccountingTypeService accountingTypeService;

    @Autowired
    SessionService sessionService;

    @Autowired
    UserService userService;

    @Autowired
    PeriodService periodService;

    private static final String ERROR_PAGE = "core/error/index";
    private static final String INDEX_PAGE = "finance/accounting/index";
    private static final String SHOW_PAGE = "finance/accounting/show";
    private static final String CREATE_PAGE = "finance/accounting/create";
    private static final String EDIT_PAGE = "finance/accounting/edit";

    @Override
    @GetMapping(value = {"/", "", "/index"})
    public String index(Model model) {
        model.addAttribute("accountings",accountingService.getAllAccountingsByUserId(sessionService.getSessionUser().getUser().getId()));
        return INDEX_PAGE;
    }

    @GetMapping(value = {"/show/{id}", "/{id}"})
    public String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("accounting",accountingService.getAccountingById(id));
        return SHOW_PAGE;
    }

    @Override
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("accountingTypes", accountingTypeService.getAllAccountingTypes());
        model.addAttribute("periods", periodService.getAllPeriods());
        model.addAttribute("accounting", new Accounting());
        return CREATE_PAGE;
    }

    @Override
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int Id, Model model) {
        model.addAttribute("accounting", accountingService.getAccountingById(Id));
        return EDIT_PAGE;
    }

    @Override
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int Id, Model model) {
        try {
            accountingService.deleteAccounting(accountingService.getAccountingById(Id));
            return INDEX_PAGE;
        } catch (Exception ex) {
            model.addAttribute("error",new ErrorResponse(500,"Internal Server error!"));
            return ERROR_PAGE;
        }
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("accounting") Accounting accounting, Model model) {
        try {
            int periodId = accounting.getPeriod().getId();
            int accountingTypeId = accounting.getAccountingType().getId();
            accounting.setPeriod(periodService.getPeriodById(periodId));
            accounting.setAccountingType(accountingTypeService.getAccountingTypeById(accountingTypeId));
            accountingService.saveAccounting(accounting);
            model.addAttribute("accountings", accountingService.getAllAccountingsByUserId(sessionService.getSessionUser().getUser().getId()));
            model.addAttribute("messageType","success");
            model.addAttribute("message","Accounting successfully created!");
            return INDEX_PAGE;
        } catch (Exception ex) {
            model.addAttribute("accountings", accountingService.getAllAccountingsByUserId(sessionService.getSessionUser().getUser().getId()));
            model.addAttribute("messageType","error");
            model.addAttribute("message","Error creating accounting: " + ex.getMessage());
            return INDEX_PAGE;
        }
    }
}
