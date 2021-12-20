package com.gomes.dataregister.api.controller;

import com.gomes.dataregister.model.User;
import com.gomes.dataregister.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/users")
public class UserApiController {

    @Autowired
    public UserService userService;

    @GetMapping(value = "/test")
    public String test() {
        return userService.test();
    }

    @GetMapping(value="/")
    public Iterable<User> index() {
        return userService.getAllUsers();
    }

}
