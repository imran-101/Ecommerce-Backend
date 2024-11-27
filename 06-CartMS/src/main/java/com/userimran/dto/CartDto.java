package com.userimran.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartDto {
    private Integer cartId;
    private Integer productId;
    private Integer userId;
    private Integer quantity;
}
