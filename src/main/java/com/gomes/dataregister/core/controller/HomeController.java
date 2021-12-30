package com.gomes.dataregister.core.controller;

import com.gomes.dataregister.admin.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = {"/"})
public class HomeController implements BasicController {

    @Autowired
    SessionService sessionService;

    private static final String INDEX_PAGE = "core/home/index";

    @Override
    @GetMapping(value = {"/",""})
    public String index(Model model) {
        model.addAttribute("user",sessionService.getSessionUser().getUser());
        return INDEX_PAGE;
    }

    @Override
    public String create(Model model) {
        return null;
    }

    @Override
    public String edit(int Id, Model model) {
        return null;
    }

    @Override
    public String delete(int Id, Model model) {
        return null;
    }
}
