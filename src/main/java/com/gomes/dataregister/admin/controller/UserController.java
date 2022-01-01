package com.gomes.dataregister.admin.controller;

import com.gomes.dataregister.core.dataobject.LogDataObject;
import com.gomes.dataregister.admin.model.User;
import com.gomes.dataregister.core.response.ErrorResponse;
import com.gomes.dataregister.admin.service.LogService;
import com.gomes.dataregister.admin.service.SessionService;
import com.gomes.dataregister.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    LogService logService;

    @Autowired
    SessionService sessionService;

    private static final String ERROR_PAGE = "core/error/index";
    private static final String INDEX_PAGE = "admin/user/index";
    private static final String SHOW_PAGE = "admin/user/show";
    private static final String CREATE_PAGE = "admin/user/create";
    private static final String EDIT_PAGE = "admin/user/edit";

    @GetMapping(value={"/",""})
    public String index(Model model, HttpSession session) {
        User sessionUser = sessionService.getSessionUser().getUser();
        model.addAttribute("sessionUser",sessionUser);
        try {
            Iterable<User> users = userService.getAllUsers();
            model.addAttribute("users", users);
            model.addAttribute("sessionUser", sessionService.getSessionUser().getUser());
            return INDEX_PAGE;
        } catch (Exception ex) {
            ErrorResponse errorResponse = new ErrorResponse(500,ex.getLocalizedMessage());
            model.addAttribute("error",errorResponse);
            return ERROR_PAGE;
        }
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int userId, Model model) {
        try {
            User user = userService.getUserById(userId);
            model.addAttribute("user",user);
            return SHOW_PAGE;
        } catch (Exception ex) {
            ErrorResponse errorResponse = new ErrorResponse(500,ex.getLocalizedMessage());
            model.addAttribute("error",errorResponse);
            return ERROR_PAGE;
        }
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("user", new User());
        return CREATE_PAGE;
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
            return INDEX_PAGE;
        } catch (Exception ex) {
            model.addAttribute("message","Erro ao criar/editar usuário." + ex.getLocalizedMessage());
            model.addAttribute("messageType", "error");
            return INDEX_PAGE;
        }
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int userId, Model model) {
        model.addAttribute("user", userService.getUserById(userId));
        return EDIT_PAGE;
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
            return INDEX_PAGE;
        } catch (Exception ex) {
            model.addAttribute("users", userService.getAllUsers());
            model.addAttribute("message","Erro ao apagar usuário." + ex.getLocalizedMessage());
            model.addAttribute("messageType", "error");
            return INDEX_PAGE;
        }
    }


}
