package com.gomes.dataregister.response;

public class ErrorResponse extends ApiResponse{
    public ErrorResponse(int statusCode, String message) {
        super(statusCode, message);
    }
}
