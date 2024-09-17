package com.userimran.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    private String productName;
    private String description;
    private Double price;
    private Integer stock;
    private Integer discount;
    private String productImageUrl;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
}
