package com.userimran.service;

import com.userimran.dto.OrderDto;

import java.time.LocalDate;
import java.util.List;

public interface OrderService {
    List<OrderDto> addOrder(List<OrderDto> order);
    OrderDto updateOrder(OrderDto order);
    List<OrderDto> getOrdersByUserId(int userId);
    List<OrderDto> getOrderByDateAndStatus(LocalDate orderDate, String orderStatus);
    List<OrderDto> getAllOrders();
}
