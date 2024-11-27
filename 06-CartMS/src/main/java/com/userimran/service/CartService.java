package com.userimran.service;

import com.userimran.dto.CartDto;

public interface CartService {
    CartDto addToCart(CartDto cartDto);
    CartDto updateCartQuantityById(CartDto cartDto);
    CartDto getCartByUserId(Integer id);
    CartDto deleteCartById(Integer id);
}
