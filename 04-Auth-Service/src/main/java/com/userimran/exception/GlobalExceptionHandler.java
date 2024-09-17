package com.userimran.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthServiceException.class)
    public ResponseEntity<ExResponse> handleAuthServiceException(AuthServiceException ae){
        ExResponse exResponse = ExResponse.builder().errCode("Ec101").errMsg(ae.getMessage()).build();
        return new ResponseEntity<>(exResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
