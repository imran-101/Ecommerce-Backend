package com.userimran.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;
    private String productName;
    private String description;
    private Double price;
    private Integer stock;
    private String productImageUrl;
    private Integer discount;
    private Double priceBeforeDiscount;

    @CreationTimestamp
    @Column(name = "created_dt", updatable = false)
    private LocalDate createdDate;

    @UpdateTimestamp
    @Column(name = "updated_dt", insertable = false)
    private LocalDate updatedDate;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
}
