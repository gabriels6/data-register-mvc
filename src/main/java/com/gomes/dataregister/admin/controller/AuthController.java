package com.gomes.dataregister.admin.controller;


import com.gomes.dataregister.core.model.User;
import com.gomes.dataregister.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    private static final String LOGIN_PAGE = "admin/auth/login";
    private static final String SIGNIN_PAGE = "admin/auth/signin";

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return LOGIN_PAGE;
    }

    @GetMapping("/signin")
    public String signin(Model model) {
        model.addAttribute("user", new User());
        return SIGNIN_PAGE;
    }

}
