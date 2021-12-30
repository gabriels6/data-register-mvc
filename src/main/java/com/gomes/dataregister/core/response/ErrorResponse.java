package com.gomes.dataregister.core.response;

public class ErrorResponse extends ApiResponse{
    public ErrorResponse(int statusCode, String message) {
        super(statusCode, message);
    }
}
