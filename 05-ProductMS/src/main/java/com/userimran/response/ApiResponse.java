package com.userimran.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse<T> {
    private Integer statusCode;
    private String message;
    private T data;
}
