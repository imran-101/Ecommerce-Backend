package com.userimran.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ProductOrderDto {
    private Integer orderId;
    private Integer userId;
    private Integer productId;
    private Integer quantity;
    private Double price;
    private String paymentType;
    private String orderStatus;
    private LocalDate orderDate;
}
