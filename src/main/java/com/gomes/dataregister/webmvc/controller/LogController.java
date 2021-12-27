package com.gomes.dataregister.webmvc.controller;

import com.gomes.dataregister.dataobject.LogDataObject;
import com.gomes.dataregister.model.Log;
import com.gomes.dataregister.model.User;
import com.gomes.dataregister.response.ErrorResponse;
import com.gomes.dataregister.response.LogApiResponse;
import com.gomes.dataregister.service.LogService;
import com.gomes.dataregister.service.UserService;
import com.gomes.dataregister.utils.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/log")
public class LogController implements BasicController{

    @Autowired
    LogService logService;

    @Autowired
    UserService userService;

    MessageUtils messageUtils = new MessageUtils();

    @Override
    @GetMapping(value = {"/",""})
    public String index(Model model) {
        model.addAttribute("logs",logService.getAllLogs().getLogs());
        return "log/index";
    }

    @GetMapping(value = "/{id}")
    public String show(@PathVariable("id") int Id, Model model) {
        try {
            model.addAttribute("log", logService.getLogById(Id).getLog());
            return "log/show";
        } catch (Exception ex) {
            ErrorResponse errorResponse = new ErrorResponse(500,ex.getLocalizedMessage());
            return "error/index";
        }
    }

    @Override
    @GetMapping("/create")
    public String create(Model model) {
        ErrorResponse errorResponse = new ErrorResponse(500,"Screen not yet implemented.");
        model.addAttribute("logDto",new LogDataObject());
        return "log/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute("logDto") LogDataObject logDto, Model model) {
        Log log = new Log();
        User user = userService.getUserById(logDto.getUser_id());
        if(logDto.getId() != 0) {
            log.setId(logDto.getId());
        }
        log.setUser(user);
        log.setDate(logDto.getDate());
        log.setDescription(logDto.getDescription());
        LogApiResponse response = logService.saveLog(log);
        if(response.getStatusCode() == 500) {
            model.addAttribute("message",response.getMessage());
            model.addAttribute("messageType",messageUtils.ERROR_MESSAGE_TYPE);
            return "log/create";
        }
        model.addAttribute("log", response.getLog());
        return "log/show";
    }

    @Override
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") int Id, Model model) {
        try {
            LogDataObject logDto = new LogDataObject();
            Log log = logService.getLogById(Id).getLog();
            logDto.setId(log.getId());
            logDto.setUser_id(log.getUser().getId());
            logDto.setDescription(log.getDescription());
            logDto.setDate(log.getDate());
            model.addAttribute("logDto",logDto);
            return "log/edit";
        } catch (Exception ex) {
            model.addAttribute("message","Erro no servidor.");
            model.addAttribute("messageType",messageUtils.ERROR_MESSAGE_TYPE);
            return "log/index";
        }
    }

    @Override
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") int Id, Model model) {
        try {
            LogDataObject logDto = new LogDataObject();
            Log logToDelete = new Log();
            logToDelete.setId(Id);
            logService.deleteLog(logToDelete).getLog();
            model.addAttribute("logs", logService.getAllLogs().getLogs());
            model.addAttribute("logDto",logDto);
            return "log/index";
        } catch (Exception ex) {
            model.addAttribute("message","Erro no servidor.");
            model.addAttribute("messageType",messageUtils.ERROR_MESSAGE_TYPE);
            return "log/index";
        }
    }
}
