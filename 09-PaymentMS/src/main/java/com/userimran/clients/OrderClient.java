package com.userimran.clients;

import com.userimran.dto.ProductOrderDto;
import com.userimran.response.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "OrderMS")
public interface OrderClient {

    @PutMapping("/order/api/order")
    ResponseEntity<ApiResponse<ProductOrderDto>> updateOrder(@RequestBody ProductOrderDto productOrderDto);
}
