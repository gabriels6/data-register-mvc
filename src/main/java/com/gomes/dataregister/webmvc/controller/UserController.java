package com.gomes.dataregister.webmvc.controller;

import com.gomes.dataregister.dataobject.LogDataObject;
import com.gomes.dataregister.model.User;
import com.gomes.dataregister.response.ErrorResponse;
import com.gomes.dataregister.service.LogService;
import com.gomes.dataregister.service.SessionService;
import com.gomes.dataregister.service.UserService;
import org.hibernate.hql.internal.ast.ErrorReporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    LogService logService;

    @Autowired
    SessionService sessionService;

    @GetMapping(value={"/",""})
    public String index(Model model, HttpSession session) {
        User sessionUser = sessionService.getSessionUser().getUser();
        model.addAttribute("sessionUser",sessionUser);
        try {
            Iterable<User> users = userService.getAllUsers();
            model.addAttribute("users", users);
            model.addAttribute("sessionUser", sessionService.getSessionUser().getUser());
            return "user/index";
        } catch (Exception ex) {
            ErrorResponse errorResponse = new ErrorResponse(500,ex.getLocalizedMessage());
            model.addAttribute("error",errorResponse);
            return "error/index";
        }
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int userId, Model model) {
        try {
            User user = userService.getUserById(userId);
            model.addAttribute("user",user);
            return "user/show";
        } catch (Exception ex) {
            ErrorResponse errorResponse = new ErrorResponse(500,ex.getLocalizedMessage());
            model.addAttribute("error",errorResponse);
            return "error/index";
        }
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("user", new User());
        return "user/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("user") User user, Model model) {
        User sessionUser = sessionService.getSessionUser().getUser();
        model.addAttribute("sessionUser",sessionUser);
        try {
            userService.saveUser(user);
            model.addAttribute("message","Usuário criado/editado com sucesso");
            model.addAttribute("messageType", "success");
            Iterable<User> users = userService.getAllUsers();
            model.addAttribute("users", users);
            LogDataObject logDto = new LogDataObject();
            logService.saveLogRaw("Usuario de nome = " + user.getUsername() + " criado/alterado com sucesso",logDto.getDate(),sessionService.getSessionUser().getUser().getId());
            return "user/index";
        } catch (Exception ex) {
            model.addAttribute("message","Erro ao criar/editar usuário." + ex.getLocalizedMessage());
            model.addAttribute("messageType", "error");
            return "user/index";
        }
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int userId, Model model) {
        model.addAttribute("user", userService.getUserById(userId));
        return "user/edit";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int userId, Model model) {
        User sessionUser = sessionService.getSessionUser().getUser();
        model.addAttribute("sessionUser",sessionUser);
        try {
            if(sessionUser.getId() == userId) {
                throw new Exception("Tentativa de remoção do usuário em sessão.");
            }
            userService.deleteUser(userService.getUserById(userId));
            model.addAttribute("message","Usuário apagado com sucesso");
            model.addAttribute("messageType", "success");
            model.addAttribute("users", userService.getAllUsers());
            LogDataObject logDto = new LogDataObject();
            logService.saveLogRaw("Usuario de Id = " + userId + " deletado com sucesso.",logDto.getDate(),sessionUser.getId());
            return "user/index";
        } catch (Exception ex) {
            model.addAttribute("users", userService.getAllUsers());
            model.addAttribute("message","Erro ao apagar usuário." + ex.getLocalizedMessage());
            model.addAttribute("messageType", "error");
            return "user/index";
        }
    }


}
