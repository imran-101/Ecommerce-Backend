package com.userimran.mapper;

import com.userimran.dto.CartDto;
import com.userimran.entity.Cart;
import org.modelmapper.ModelMapper;

public class CartMapper {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static Cart convertToEntity(CartDto cartDto) {
        return modelMapper.map(cartDto, Cart.class);
    }

    public static CartDto convertToDto(Cart cart) {
        return modelMapper.map(cart, CartDto.class);
    }
}
