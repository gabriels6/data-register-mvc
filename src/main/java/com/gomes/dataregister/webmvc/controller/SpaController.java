package com.gomes.dataregister.webmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/spa")
public class SpaController {

    @GetMapping(value = {"/",""})
    public String renderPage(Model model){
        model.addAttribute("page","https://schedule-project.netlify.app");
        return "spa/control";
    }

}
