package com.gomes.dataregister.response;

import com.gomes.dataregister.model.Log;

public class LogApiResponse extends ApiResponse{

    private Log log;

    private Iterable<Log> logs;

    public LogApiResponse(int statusCode, String message) {
        super(statusCode, message);
    }

    public Log getLog() {
        return log;
    }

    public void setLog(Log log) {
        this.log = log;
    }

    public Iterable<Log> getLogs() {
        return logs;
    }

    public void setLogs(Iterable<Log> logs) {
        this.logs = logs;
    }
}
