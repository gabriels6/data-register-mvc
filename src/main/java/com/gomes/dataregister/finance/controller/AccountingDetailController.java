package com.gomes.dataregister.finance.controller;

import com.gomes.dataregister.core.controller.BasicController;
import com.gomes.dataregister.core.service.SessionService;
import com.gomes.dataregister.finance.model.AccountingDetail;
import com.gomes.dataregister.finance.service.AccountingDetailService;
import com.gomes.dataregister.finance.service.AccountingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/finance/accounting/detail")
public class AccountingDetailController implements BasicController {

    @Autowired
    AccountingDetailService accountingDetailService;

    @Autowired
    AccountingService accountingService;

    @Autowired
    SessionService sessionService;

    private static final String ERROR_PAGE = "core/error/index";
    private static final String INDEX_PAGE = "finance/accountingDetail/index";
    private static final String SHOW_PAGE = "finance/accountingDetail/show";
    private static final String CREATE_PAGE = "finance/accountingDetail/create";
    private static final String EDIT_PAGE = "finance/accountingDetail/edit";

    @Override
    @GetMapping(value = {"/", "", "/index"})
    public String index(Model model) {
        model.addAttribute("accountingDetails", accountingDetailService.getAllAccountingDetails());
        return INDEX_PAGE;
    }

    @Override
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("accountings", accountingService.getAllAccountingsByUserId(sessionService.getSessionUser().getUser().getId()));
        model.addAttribute("accountingDetail", new AccountingDetail());
        return CREATE_PAGE;
    }

    @GetMapping(value = {"/{id}", "/show/{id}"})
    public String show(@PathVariable("id") int Id, Model model) {
        model.addAttribute("accountingDetail", accountingDetailService.getAccountingDetailById(Id));
        return SHOW_PAGE;
    }

    @Override
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int Id, Model model) {
        model.addAttribute("accountings", accountingService.getAllAccountingsByUserId(sessionService.getSessionUser().getUser().getId()));
        model.addAttribute("accountingDetail", accountingDetailService.getAccountingDetailById(Id));
        return EDIT_PAGE;
    }

    @Override
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int Id, Model model) {
        try {
            accountingDetailService.deleteAccountingDetail(accountingDetailService.getAccountingDetailById(Id));
            return indexWithMessage("success","Accounting Detail sucessfully deleted! ",model);
        } catch (Exception ex) {
            return indexWithMessage("error","Error deleting accounting detail!", model);
        }
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("accountingDetail") AccountingDetail accountingDetail, Model model) {
        try {
            int accId = accountingDetail.getAccounting().getId();
            accountingDetail.setAccounting(accountingService.getAccountingById(accId));
            accountingDetailService.saveAccountingDetail(accountingDetail);
            return indexWithMessage("success", "Accounting Detail successfully created!", model);
        } catch (Exception ex) {
            return indexWithMessage("error","Error creating accounting detail: " + ex.getMessage(), model);
        }
    }

    public String indexWithMessage(String messageType, String message, Model model) {
        model.addAttribute("accountingDetails", accountingDetailService.getAllAccountingDetails());
        model.addAttribute("messageType",messageType);
        model.addAttribute("message",message);
        return INDEX_PAGE;
    }
}
