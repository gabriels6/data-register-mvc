package com.gomes.dataregister.finance.controller;

import com.gomes.dataregister.core.controller.BasicController;
import com.gomes.dataregister.core.utils.DateUtils;
import com.gomes.dataregister.finance.model.Period;
import com.gomes.dataregister.finance.service.PeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@Controller
@RequestMapping("/finance/period")
public class PeriodController implements BasicController {

    @Autowired
    PeriodService periodService;

    private static final String ERROR_PAGE = "core/error/index";
    private static final String INDEX_PAGE = "finance/period/index";
    private static final String SHOW_PAGE = "finance/period/show";
    private static final String CREATE_PAGE = "finance/period/create";
    private static final String EDIT_PAGE = "finance/period/edit";

    @Override
    @GetMapping(value = {"/", "", "/index"})
    public String index(Model model) {
        model.addAttribute("periods", periodService.getAllPeriods());
        return INDEX_PAGE;
    }

    @Override
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("months", new DateUtils().getMonthNumbers());
        model.addAttribute("period", new Period());
        return CREATE_PAGE;
    }

    @Override
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int Id, Model model) {
        model.addAttribute("months", new DateUtils().getMonthNumbers());
        model.addAttribute("period", periodService.getPeriodById(Id));
        return EDIT_PAGE;
    }

    @GetMapping(value = {"/show/{id}", "/{id}"})
    public String show(@PathVariable("id") int Id, Model model) {
        model.addAttribute("period", periodService.getPeriodById(Id));
        return SHOW_PAGE;
    }

    @Override
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int Id, Model model) {
        try {
            periodService.deletePeriod(periodService.getPeriodById(Id));
            model.addAttribute("messageType","success");
            model.addAttribute("message","Period sucessfully deleted! ");
            model.addAttribute("periods", periodService.getAllPeriods());
            return INDEX_PAGE;
        } catch (Exception ex) {
            model.addAttribute("messageType","error");
            model.addAttribute("message","Error deleting period: " + ex.getMessage());
            model.addAttribute("periods", periodService.getAllPeriods());
            return INDEX_PAGE;
        }
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("period") Period period, Model model) {
        try{
            periodService.savePeriod(period);
            model.addAttribute("periods", periodService.getAllPeriods());
            model.addAttribute("messageType","success");
            model.addAttribute("message","Period sucessfully created! ");
            return INDEX_PAGE;
        } catch (Exception ex) {
            model.addAttribute("messageType","error");
            model.addAttribute("message","Error creating period: " + ex.getMessage());
            model.addAttribute("periods", periodService.getAllPeriods());
            return INDEX_PAGE;
        }
    }
}
