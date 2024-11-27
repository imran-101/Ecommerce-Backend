package com.userimran.service;

import com.userimran.dto.CartDto;
import com.userimran.entity.Cart;
import com.userimran.mapper.CartMapper;
import com.userimran.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    @Override
    public CartDto addToCart(CartDto cartDto) {
        Cart cart = CartMapper.convertToEntity(cartDto);
        return CartMapper.convertToDto(cartRepository.save(cart));
    }

    @Override
    public CartDto updateCartQuantityById(CartDto cartDto) {
        Cart cart = cartRepository.findById(cartDto.getCartId()).orElseThrow(() -> new NoSuchElementException("Cart not found for ID: " + cartDto.getCartId()));
        cart.setProductId(cartDto.getProductId());
        cart.setQuantity(cartDto.getQuantity());
        return CartMapper.convertToDto(cartRepository.save(cart));
    }

    @Override
    public CartDto getCartByUserId(Integer userId) {
        Cart cart = cartRepository.findById(userId).orElseThrow(() -> new NoSuchElementException("Cart not found for ID: " + userId));
        return CartMapper.convertToDto(cart);
    }

    @Override
    public CartDto deleteCartById(Integer id) {
        Cart cart = cartRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Cart not found for ID: " + id));
        cartRepository.deleteById(id);
        return CartMapper.convertToDto(cart);
    }
}
