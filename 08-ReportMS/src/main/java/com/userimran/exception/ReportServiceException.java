package com.userimran.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReportServiceException extends RuntimeException {
    private String errorCode;
    public ReportServiceException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }
}
