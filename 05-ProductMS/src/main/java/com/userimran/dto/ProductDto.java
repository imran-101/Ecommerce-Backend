package com.userimran.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    private Integer productId;
    private String productName;
    private String description;
    private Double price;
    private Integer stock;
    private String productImageUrl;
    private Integer discount;
    private Double priceBeforeDiscount;
    private Integer categoryId;
    private CategoryDto categoryDto;
}
