package com.gomes.dataregister.api.controller;

import com.gomes.dataregister.core.response.LogApiResponse;
import com.gomes.dataregister.admin.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/logs")
public class LogApiController {

    @Autowired
    public LogService logService;

    @GetMapping("/")
    public LogApiResponse index() {
        return logService.getAllLogs();
    }

    @GetMapping("/{id}")
    public LogApiResponse indexById(@PathVariable(name = "id") int logId) {
        return logService.getLogById(logId);
    }

}
