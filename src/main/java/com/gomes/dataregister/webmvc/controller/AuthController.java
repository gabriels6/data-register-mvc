package com.gomes.dataregister.webmvc.controller;


import com.gomes.dataregister.model.User;
import com.gomes.dataregister.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "auth/login";
    }

    @GetMapping("/signin")
    public String signin(Model model) {
        model.addAttribute("user", new User());
        return "auth/signin";
    }

}
