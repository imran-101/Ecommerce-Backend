package com.userimran.controller;

import com.razorpay.Order;
import com.razorpay.RazorpayException;
import com.userimran.constants.AppConstants;
import com.userimran.dto.OrderResponse;
import com.userimran.dto.PaymentRequest;
import com.userimran.mapper.OrderMapper;
import com.userimran.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/payment/api")
@RequiredArgsConstructor
public class PaymentRestController {

    private final PaymentService paymentService;

    @PostMapping(value = "/initiate-payment", produces = "application/json")
    public ResponseEntity<OrderResponse> createOrder(@RequestBody PaymentRequest paymentRequest)
            throws RazorpayException, RazorpayException {
        Order order = paymentService.createOrder(paymentRequest);
        OrderResponse convertToOrderResponse = OrderMapper.convertToOrderResponse(order);
        if (convertToOrderResponse != null)
            return new ResponseEntity<>(convertToOrderResponse, HttpStatus.OK);
        return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/payment-callback")
    public ResponseEntity<String> handlePaymentCallback(@RequestBody Map<String, Object> payload) {
        boolean verifyPaymentSignature = paymentService.verifyPaymentSignature(payload);
        if (verifyPaymentSignature) {
            return new ResponseEntity<>(AppConstants.SUCCESS_MSG, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(AppConstants.FAILED_MSG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
