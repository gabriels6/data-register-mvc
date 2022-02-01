package com.gomes.dataregister.finance.controller;


import com.gomes.dataregister.core.controller.BasicController;
import com.gomes.dataregister.core.response.ErrorResponse;
import com.gomes.dataregister.finance.model.AccountingType;
import com.gomes.dataregister.finance.service.AccountingTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@Controller
@RequestMapping("/finance/accounting/type")
public class AccountingTypeController implements BasicController {

    @Autowired
    AccountingTypeService typeService;

    private static final String ERROR_PAGE = "core/error/index";
    private static final String INDEX_PAGE = "finance/accountingType/index";
    private static final String SHOW_PAGE = "finance/accountingType/show";
    private static final String CREATE_PAGE = "finance/accountingType/create";
    private static final String EDIT_PAGE = "finance/accountingType/edit";

    @Override
    @GetMapping(value = {"/", "", "/index"})
    public String index(Model model) {
        model.addAttribute("accTypes",typeService.getAllAccountingTypes());
        return INDEX_PAGE;
    }

    @GetMapping(value = {"/show/{id}", "/{id}"})
    public String show(@PathVariable("id") int Id, Model model) {
        model.addAttribute("accType",typeService.getAccountingTypeById(Id));
        return SHOW_PAGE;
    }

    @Override
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("accType",new AccountingType());
        return CREATE_PAGE;
    }

    @Override
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int Id, Model model) {
        model.addAttribute("accType",typeService.getAccountingTypeById(Id));
        return EDIT_PAGE;
    }

    @Override
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id, Model model) {
        try {
            typeService.deleteAccountingType(typeService.getAccountingTypeById(id));
            model.addAttribute("accTypes",typeService.getAllAccountingTypes());
            return INDEX_PAGE;
        } catch (Exception ex) {
            model.addAttribute("error", new ErrorResponse(500,"Internal Server Error:" + ex.getLocalizedMessage()));
            return ERROR_PAGE;
        }
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("accType") AccountingType accType, Model model) {
        try {
            typeService.saveAccountingType(accType);
            model.addAttribute("accTypes", typeService.getAllAccountingTypes());
            model.addAttribute("messageType","success");
            model.addAttribute("message","Accounting Type successfully Created!");
            return INDEX_PAGE;
        } catch (Exception ex) {
            ErrorResponse errorResponse = new ErrorResponse(500,"Internal Server Error: " + ex.getLocalizedMessage());
            model.addAttribute("message",errorResponse.getMessage());
            model.addAttribute("messageType", "error");
            return INDEX_PAGE;
        }
    }
}
