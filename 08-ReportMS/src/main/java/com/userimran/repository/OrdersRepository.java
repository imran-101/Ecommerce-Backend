package com.userimran.repository;

import com.userimran.entity.Orders;
import com.userimran.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDate;
import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Integer>, JpaSpecificationExecutor<Orders> {
    List<Orders> findByOrderStatus(OrderStatus status);

    List<Orders> findByOrderDateBetween(LocalDate startDate, LocalDate endDate);
}
