package com.userimran.controller;

import com.userimran.constants.AppConstants;
import com.userimran.dto.OrderDto;
import com.userimran.props.AppProps;
import com.userimran.response.ApiResponse;
import com.userimran.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order/api")
@RequiredArgsConstructor
public class OrderRestController {
    private final OrderService orderService;
    private final AppProps props;

    @PostMapping("/orders")
    public ResponseEntity<ApiResponse<List<OrderDto>>> addOrder(@RequestBody List<OrderDto> orderDto) {
        ApiResponse<List<OrderDto>> response = new ApiResponse<>();
        Map<String, String> messages = props.getMessages();
        List<OrderDto> addedOrders = orderService.addOrder(orderDto);
        if (addedOrders != null) {
            response.setStatusCode(200);
            response.setMessage(messages.get(AppConstants.ADD_ORDERS));
            response.setData(addedOrders);
        } else {
            response.setStatusCode(500);
            response.setMessage(messages.get(AppConstants.ADD_ORDERS_ERR));
        }
        return ResponseEntity.ok(response);
    }

    @PutMapping("/order")
    public ResponseEntity<ApiResponse<OrderDto>> updateOrder(@RequestBody OrderDto orderDto) {
        ApiResponse<OrderDto> response = new ApiResponse<>();
        Map<String, String> messages = props.getMessages();
        OrderDto updatedOrder = orderService.updateOrder(orderDto);
        if (updatedOrder != null) {
            response.setStatusCode(200);
            response.setMessage(messages.get(AppConstants.UPDATE_ORDER));
            response.setData(updatedOrder);
        } else {
            response.setStatusCode(500);
            response.setMessage(messages.get(AppConstants.UPDATE_ORDER_ERR));
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/orders/{userId}")
    public ResponseEntity<ApiResponse<List<OrderDto>>> getOrdersByUserId(@PathVariable("userId") Integer userId) {
        ApiResponse<List<OrderDto>> response = new ApiResponse<>();
        Map<String, String> messages = props.getMessages();
        List<OrderDto> ordersByUserId = orderService.getOrdersByUserId(userId);
        if (ordersByUserId != null) {
            response.setStatusCode(200);
            response.setMessage(messages.get(AppConstants.SELECT_ORDERS_USERID));
            response.setData(ordersByUserId);
        } else {
            response.setStatusCode(500);
            response.setMessage(messages.get(AppConstants.SELECT_ORDERS_USERID_ERR));
        }
        return ResponseEntity.ok(response);
    }


    @GetMapping("/orders/{orderDate}/{orderStatus}")
    public ResponseEntity<ApiResponse<List<OrderDto>>> getOrderByDateAndStatus(
            @PathVariable(value = "orderDate") LocalDate orderDate,
            @PathVariable(value = "orderStatus") String orderStatus) {

        ApiResponse<List<OrderDto>> response = new ApiResponse<>();
        Map<String, String> messages = props.getMessages();
        List<OrderDto> ordersByDateAndStatus = orderService.getOrderByDateAndStatus(orderDate, orderStatus);
        if (ordersByDateAndStatus != null) {
            response.setStatusCode(200);
            response.setMessage(messages.get(AppConstants.SELECT_ORDERS_DATE_AND_STATUS));
            response.setData(ordersByDateAndStatus);
        } else {
            response.setStatusCode(500);
            response.setMessage(messages.get(AppConstants.SELECT_ORDERS_DATE_AND_STATUS_ERR));
        }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/orders")
    public ResponseEntity<ApiResponse<List<OrderDto>>> getAllOrders() {
        ApiResponse<List<OrderDto>> response = new ApiResponse<>();
        Map<String, String> messages = props.getMessages();
        List<OrderDto> allOrders = orderService.getAllOrders();
        if (allOrders != null) {
            response.setStatusCode(200);
            response.setMessage(messages.get(AppConstants.FETCH_ORDERS));
            response.setData(allOrders);
        } else {
            response.setStatusCode(500);
            response.setMessage(messages.get(AppConstants.FETCH_ORDERS_ERR));
        }
        return ResponseEntity.ok(response);
    }
}
