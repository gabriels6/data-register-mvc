package com.gomes.dataregister.admin.controller;

import com.gomes.dataregister.core.dataobject.LogDataObject;
import com.gomes.dataregister.admin.model.Log;
import com.gomes.dataregister.core.model.User;
import com.gomes.dataregister.core.controller.BasicController;
import com.gomes.dataregister.core.response.ErrorResponse;
import com.gomes.dataregister.core.response.LogApiResponse;
import com.gomes.dataregister.admin.service.LogService;
import com.gomes.dataregister.core.service.UserService;
import com.gomes.dataregister.core.utils.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/log")
public class LogController implements BasicController {

    @Autowired
    LogService logService;

    @Autowired
    UserService userService;

    MessageUtils messageUtils = new MessageUtils();

    private static final String INDEX_PAGE = "admin/log/index";
    private static final String CREATE_PAGE = "admin/log/create";
    private static final String EDIT_PAGE = "admin/log/edit";
    private static final String SHOW_PAGE = "admin/log/show";
    private static final String ERROR_PAGE = "core/error/index";

    @Override
    @GetMapping(value = {"/",""})
    public String index(Model model) {
        model.addAttribute("logs",logService.getAllLogs().getLogs());
        return INDEX_PAGE;
    }

    @GetMapping(value = "/{id}")
    public String show(@PathVariable("id") int Id, Model model) {
        try {
            model.addAttribute("log", logService.getLogById(Id).getLog());
            return SHOW_PAGE;
        } catch (Exception ex) {
            ErrorResponse errorResponse = new ErrorResponse(500,ex.getLocalizedMessage());
            return ERROR_PAGE;
        }
    }

    @Override
    @GetMapping("/create")
    public String create(Model model) {
        ErrorResponse errorResponse = new ErrorResponse(500,"Screen not yet implemented.");
        model.addAttribute("logDto",new LogDataObject());
        return CREATE_PAGE;
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
            return CREATE_PAGE;
        }
        model.addAttribute("log", response.getLog());
        return SHOW_PAGE;
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
            return EDIT_PAGE;
        } catch (Exception ex) {
            model.addAttribute("message","Erro no servidor.");
            model.addAttribute("messageType",messageUtils.ERROR_MESSAGE_TYPE);
            return INDEX_PAGE;
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
            return INDEX_PAGE;
        } catch (Exception ex) {
            model.addAttribute("message","Erro no servidor.");
            model.addAttribute("messageType",messageUtils.ERROR_MESSAGE_TYPE);
            return INDEX_PAGE;
        }
    }
}
