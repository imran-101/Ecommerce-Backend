package com.userimran.exception;

public class AuthServiceException extends RuntimeException {
    public AuthServiceException() {
        super();
    }

    public AuthServiceException(String message) {
        super(message);
    }
}
