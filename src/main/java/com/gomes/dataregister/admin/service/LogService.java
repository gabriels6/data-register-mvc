package com.gomes.dataregister.admin.service;

import com.gomes.dataregister.admin.model.Log;
import com.gomes.dataregister.core.model.User;
import com.gomes.dataregister.admin.repository.LogRepository;
import com.gomes.dataregister.core.response.LogApiResponse;
import com.gomes.dataregister.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class LogService {

    @Autowired
    private UserService userService;

    @Autowired
    private LogRepository logRepository;

    public LogApiResponse getAllLogs() {
        LogApiResponse logApiResponse = new LogApiResponse(0,"");
        try {
            logApiResponse.setLogs(logRepository.findAll());
            logApiResponse.setStatusCode(200);
            logApiResponse.setMessage("Logs obtidos com sucesso!");
            return logApiResponse;
        } catch (Exception ex) {
            logApiResponse.setStatusCode(500);
            logApiResponse.setMessage("Erro ao obter logs!");
            return logApiResponse;
        }

    }

    public LogApiResponse getLogById(int id) {
        LogApiResponse logApiResponse = new LogApiResponse(0, "");
        try {
            Log log = logRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Log não encontrado"));
            logApiResponse.setLog(log);
            logApiResponse.setStatusCode(200);
            logApiResponse.setMessage("Busca obtida com sucesso!");
            return logApiResponse;
        } catch (Exception ex) {
            logApiResponse.setStatusCode(500);
            logApiResponse.setMessage("Erro no servidor.");
            return logApiResponse;
        }
    }

    public LogApiResponse saveLog(Log log) {
        LogApiResponse logApiResponse = new LogApiResponse(0, "");
        try {
            Log currLog = logRepository.save(log);
            logApiResponse.setLog(currLog);
            logApiResponse.setStatusCode(200);
            logApiResponse.setMessage("Busca obtida com sucesso!");
            return logApiResponse;
        } catch (Exception ex) {
            logApiResponse.setStatusCode(500);
            logApiResponse.setMessage("Erro no servidor.");
            return logApiResponse;
        }
    }

    public LogApiResponse saveLogRaw(String description, String date, int userId) {
        LogApiResponse logApiResponse = new LogApiResponse(0, "");
        try {
            Log log = new Log();
            log.setDescription(description);
            log.setDate(date);
            User user = userService.getUserById(userId);
            log.setUser(user);
            Log currLog = logRepository.save(log);
            logApiResponse.setLog(currLog);
            logApiResponse.setStatusCode(200);
            logApiResponse.setMessage("Criação feita com sucesso!");
            return logApiResponse;
        } catch (Exception ex) {
            logApiResponse.setStatusCode(500);
            logApiResponse.setMessage("Erro no servidor.");
            return logApiResponse;
        }
    }

    public LogApiResponse deleteLog(Log log) {
        LogApiResponse logApiResponse = new LogApiResponse(0, "");
        try {
            logRepository.delete(log);
            logApiResponse.setStatusCode(200);
            logApiResponse.setMessage("Remoção feita com sucesso!");
            return logApiResponse;
        } catch (Exception ex) {
            logApiResponse.setStatusCode(500);
            logApiResponse.setMessage("Erro no servidor.");
            return logApiResponse;
        }
    }

}
