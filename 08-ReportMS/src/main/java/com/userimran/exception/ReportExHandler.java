package com.userimran.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ReportExHandler {

    @ExceptionHandler(value = ReportServiceException.class)
    public ResponseEntity<ErrorResponse> orderExHandler(ReportServiceException e) {
        ErrorResponse response = new ErrorResponse();
        response.setMessage(e.getMessage());
        response.setErrorCode(e.getErrorCode());
        return ResponseEntity.internalServerError().body(response);
    }
}
