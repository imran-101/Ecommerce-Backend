package com.userimran.service;

import com.userimran.dto.OrderDto;
import com.userimran.entity.Orders;
import com.userimran.exception.OrderServiceException;
import com.userimran.mapper.OrderMapper;
import com.userimran.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public List<OrderDto> addOrder(List<OrderDto> orderDto) {
        List<Orders> ordersList = orderDto.stream().map(OrderMapper::convertToEntity).toList();
        List<Orders> orders = orderRepository.saveAll(ordersList);
        return orders.stream().map(OrderMapper::convertToDto).collect(toList());
    }

    @Override
    public OrderDto updateOrder(OrderDto orderDto) {
        Orders ordersEntity = OrderMapper.convertToEntity(orderDto);
        Orders orders = orderRepository.findById(ordersEntity.getOrderId()).orElseThrow(
                () -> new OrderServiceException("404", "Order not found with id : " + ordersEntity.getOrderId()));
        if (orders != null) {
            orders.setProductId(orderDto.getProductId());
            orders.setPrice(orderDto.getPrice());
            orders.setQuantity(orderDto.getQuantity());
            orders.setPaymentType(orderDto.getPaymentType());
            orders.setOrderDate(orderDto.getOrderDate());
            orders.setOrderStatus(orderDto.getOrderStatus());
            Orders updatedOrders = orderRepository.save(orders);
            return OrderMapper.convertToDto(updatedOrders);

        } else {
            return null;
        }
    }

    @Override
    public List<OrderDto> getOrdersByUserId(int userId) {
        List<Orders> orders = orderRepository.findByUserId(userId);
        if (orders != null && !orders.isEmpty()) {
            return orders.stream().map(OrderMapper::convertToDto).collect(toList());
        } else {
            return null;
        }
    }

    @Override
    public List<OrderDto> getOrderByDateAndStatus(LocalDate orderDate, String orderStatus) {
        List<Orders> ordersList = orderRepository.findByOrderDateAndOrderStatus(orderDate, orderStatus);
        if (ordersList != null) {
            return ordersList.stream().map(OrderMapper::convertToDto).collect(toList());
        } else {
            return null;
        }
    }

    @Override
    public List<OrderDto> getAllOrders() {
        List<Orders> ordersList = orderRepository.findAll();
        return ordersList.stream().map(OrderMapper::convertToDto).collect(toList());
    }
}
