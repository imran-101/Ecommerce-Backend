package com.userimran.repository;

import com.userimran.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findByProductName(String name);

    @Query(value = "select * from product where category_id= :categoryId", nativeQuery = true)
    List<Product> findProductByCategoryId(Integer categoryId);
}
