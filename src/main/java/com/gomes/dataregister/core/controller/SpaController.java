package com.gomes.dataregister.core.controller;

import com.gomes.dataregister.core.service.PropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/spa")
public class SpaController {

    @Autowired
    PropertiesService propertiesService;

    private static final String CONTROL_PAGE = "core/spa/control";

    @GetMapping(value = {"/{page}",""})
    public String renderPage(@PathVariable("page") String id,Model model){
        model.addAttribute("page",propertiesService.getSpaApiUrl()+"/api/v1/spa/" + id);
        return CONTROL_PAGE;
    }



}
