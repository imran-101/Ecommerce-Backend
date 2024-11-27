package com.userimran.mapper;

import com.userimran.dto.OrderDto;
import com.userimran.entity.Orders;
import org.modelmapper.ModelMapper;

public class OrderMapper {

    private static final ModelMapper modelMapper = new ModelMapper();

    public static OrderDto convertToDto(Orders orders) {
        return modelMapper.map(orders, OrderDto.class);
    }

    public static Orders convertToEntity(OrderDto orderDto) {
        return modelMapper.map(orderDto, Orders.class);
    }
}
