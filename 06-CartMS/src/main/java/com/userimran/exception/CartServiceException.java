package com.userimran.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartServiceException extends RuntimeException{
    private final String errorCode;

    public CartServiceException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
