package com.userimran.repository;

import com.userimran.entity.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<Orders, Integer> {
    List<Orders> findByOrderDateAndOrderStatus(LocalDate orderDate, String orderStatus);
    List<Orders> findByUserId(int userId);
}
