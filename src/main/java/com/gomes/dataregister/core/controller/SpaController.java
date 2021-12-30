package com.gomes.dataregister.core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/spa")
public class SpaController {

    private static final String CONTROL_PAGE = "core/spa/control";

    @GetMapping(value = {"/",""})
    public String renderPage(Model model){
        model.addAttribute("page","https://schedule-project.netlify.app");
        return CONTROL_PAGE;
    }

}
