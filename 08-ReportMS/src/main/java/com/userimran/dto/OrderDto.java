package com.userimran.dto;

import com.userimran.entity.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class OrderDto {

    private Integer orderId;
    private Integer userId;
    private Integer productId;
    private Integer quantity;
    private Double price;
    private String paymentType;
    private OrderStatus orderStatus;
    private LocalDate orderDate;

}
