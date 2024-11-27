package com.userimran.exception;

import org.hibernate.query.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class OrderExHandler {

    @ExceptionHandler(value = OrderServiceException.class)
    public ResponseEntity<ErrorResponse> orderExHandler(OrderServiceException e) {
        ErrorResponse response = new ErrorResponse();
        response.setMessage(e.getMessage());
        response.setErrorCode(e.getErrorCode());
        return ResponseEntity.internalServerError().body(response);
    }
}
