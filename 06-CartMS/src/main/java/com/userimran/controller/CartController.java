package com.userimran.controller;

import com.userimran.constants.AppConstants;
import com.userimran.dto.CartDto;
import com.userimran.props.AppProps;
import com.userimran.response.ApiResponse;
import com.userimran.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/cart/api")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    private final AppProps props;

    @PostMapping("/cart")
    public ResponseEntity<ApiResponse<CartDto>> addCart(@RequestBody CartDto cartDto) {
        ApiResponse<CartDto> response = new ApiResponse<>();
        Map<String, String> messages = props.getMessages();
        CartDto addToCart = cartService.addToCart(cartDto);
        if (addToCart != null) {
            response.setData(addToCart);
            response.setStatusCode(201);
            response.setMessage(messages.get(AppConstants.CART_ADDED_SUCCESS));
        } else {
            response.setStatusCode(500);
            response.setMessage(messages.get(AppConstants.CART_ADD_ERR));
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CartDto>> getCartByUserId(@PathVariable("id") Integer userId) {
        ApiResponse<CartDto> response = new ApiResponse<>();
        Map<String, String> messages = props.getMessages();

        CartDto getCart = cartService.getCartByUserId(userId);

        if (getCart != null) {
            response.setStatusCode(200);
            response.setMessage(messages.get(AppConstants.CART_RETRIEVE_SUCCESS));
            response.setData(getCart);
        } else {
            response.setStatusCode(500);
            response.setMessage(messages.get(AppConstants.CART_RETRIEVE_ERR));
        }
        return ResponseEntity.ok(response);
    }

    @PutMapping("/cart")
    public ResponseEntity<ApiResponse<CartDto>> updateCartQuantityById(@RequestBody CartDto cartDto) {
        ApiResponse<CartDto> response = new ApiResponse<>();
        Map<String, String> messages = props.getMessages();

        CartDto updateCart = cartService.updateCartQuantityById(cartDto);

        if (updateCart != null) {
            response.setStatusCode(200);
            response.setMessage(messages.get(AppConstants.CART_UPDATE_SUCCESS));
            response.setData(updateCart);
        } else {
            response.setStatusCode(500);
            response.setMessage(messages.get(AppConstants.CART_UPDATE_ERR));
        }
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<CartDto>> deleteCartById(@PathVariable("id") Integer cartId) {
        ApiResponse<CartDto> response = new ApiResponse<>();
        Map<String, String> messages = props.getMessages();

        CartDto deletedCart = cartService.deleteCartById(cartId);
        response.setStatusCode(200);
        response.setMessage(messages.get(AppConstants.CART_DELETE_SUCCESS));
        response.setData(deletedCart);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
