package com.userimran.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequest {
    private Integer orderId;
    private Double amount;
    private String currency;
}
