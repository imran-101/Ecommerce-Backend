package com.userimran.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductServiceException extends RuntimeException {

    private String errorCode;

    public ProductServiceException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
