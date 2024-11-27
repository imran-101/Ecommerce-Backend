package com.userimran.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderResponse {
    private String id;
    private Double amount;
    private String currency;
    private String receipt;
    private String status;
}
