package com.userimran.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppExHandler {

    @ExceptionHandler(value = CartServiceException.class)
    public ResponseEntity<ErrorResponse> cartExHandler(CartServiceException cse){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrorCode(cse.getErrorCode());
        errorResponse.setMessage(cse.getMessage());
        return ResponseEntity.internalServerError().body(errorResponse);
    }
}
