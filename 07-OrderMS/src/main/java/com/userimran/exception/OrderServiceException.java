package com.userimran.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderServiceException extends RuntimeException {
    private String errorCode;
    public OrderServiceException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
