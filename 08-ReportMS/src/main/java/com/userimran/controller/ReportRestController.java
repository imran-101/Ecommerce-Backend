package com.userimran.controller;

import com.userimran.dto.OrderDto;
import com.userimran.dto.ReportDto;
import com.userimran.props.AppProps;
import com.userimran.service.ReportService;
import com.userimran.utils.ExcelGenerator;
import com.userimran.utils.PdfGenerator;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/report/api")
@RequiredArgsConstructor
public class ReportRestController {
    private final ReportService reportService;
    private final AppProps props;


    @PostMapping("/ordersExcelSheet")
    public ResponseEntity<InputStreamResource> downloadOrdersInExcel(@RequestBody ReportDto reportDto) {

//        List<OrderDto> orders = new ArrayList<>();
//
//        if (reportDto.getOrderStatus() != null) {
////            orders = reportsService.orderByStatus(reportsDto.getStatus());
//        }
//        List<OrderDto> orders = reportService.getAllOrdersByFiltersInExcel(reportDto);

//        List<OrderDto> orders = reportService.getOrdersByExample(reportDto);

        List<OrderDto> orders = reportService.getOrdersByCriteria(reportDto);
        if (orders != null && !orders.isEmpty()) {
            try {
                ByteArrayInputStream in = ExcelGenerator.generateExcel(orders);
                InputStreamResource resource = new InputStreamResource(in);

                return ResponseEntity.ok().headers(new HttpHeaders())
                        .contentType(MediaType.APPLICATION_OCTET_STREAM)
                        .body(resource);
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @PostMapping("/ordersDownloadPdf")
    public ResponseEntity<InputStreamResource> downloadOrdersInPdf(@RequestBody ReportDto reportDto) {

//        List<OrderDto> orders = new ArrayList<>();
//
//        if (reportDto.getOrderStatus() != null) {
////            orders = reportsService.orderByStatus(reportsDto.getStatus());
//        }
//        List<OrderDto> orders = reportService.getAllOrdersByFiltersInExcel(reportDto);

//        List<OrderDto> orders = reportService.getOrdersByExample(reportDto);

        List<OrderDto> orders = reportService.getOrdersByCriteria(reportDto);
        if (orders != null && !orders.isEmpty()) {
            try {
                ByteArrayInputStream in = PdfGenerator.generatePdf(orders);
                InputStreamResource resource = new InputStreamResource(in);

                return ResponseEntity.ok().headers(new HttpHeaders())
                        .contentType(MediaType.APPLICATION_OCTET_STREAM)
                        .body(resource);
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }


//    @GetMapping("orders")
//    public ResponseEntity<ApiResponse<List<OrderDto>>> addOrder(@RequestBody List<OrderDto> orderDto) {
//        ApiResponse<List<OrderDto>> response = new ApiResponse<>();
//        Map<String, String> messages = props.getMessages();
//        List<OrderDto> addedOrders = orderService.addOrder(orderDto);
//        if (addedOrders != null) {
//            response.setStatusCode(200);
//            response.setMessage(AppConstants.ADD_ORDERS);
//            response.setData(addedOrders);
//        } else {
//            response.setStatusCode(500);
//            response.setMessage(AppConstants.ADD_ORDERS_ERR);
//        }
//        return ResponseEntity.ok(response);
//    }

//    @PutMapping("/order")
//    public ResponseEntity<ApiResponse<OrderDto>> updateOrder(@RequestBody OrderDto orderDto) {
//        ApiResponse<OrderDto> response = new ApiResponse<>();
//        Map<String, String> messages = props.getMessages();
//        OrderDto updatedOrder = orderService.updateOrder(orderDto);
//        if (updatedOrder != null) {
//            response.setStatusCode(200);
//            response.setMessage(messages.get(AppConstants.UPDATE_ORDER));
//            response.setData(updatedOrder);
//        } else {
//            response.setStatusCode(500);
//            response.setMessage(messages.get(AppConstants.UPDATE_ORDER_ERR));
//        }
//        return ResponseEntity.ok(response);
//    }


//    @GetMapping("/orders/{userId}")
//    public ResponseEntity<ApiResponse<List<OrderDto>>> getOrdersByUserId(@PathVariable("userId") Integer userId) {
//        ApiResponse<List<OrderDto>> response = new ApiResponse<>();
//        Map<String, String> messages = props.getMessages();
//        List<OrderDto> ordersByUserId = orderService.getOrdersByUserId(userId);
//        if (ordersByUserId != null) {
//            response.setStatusCode(200);
//            response.setMessage(messages.get(AppConstants.SELECT_ORDERS_USERID));
//            response.setData(ordersByUserId);
//        } else {
//            response.setStatusCode(500);
//            response.setMessage(messages.get(AppConstants.SELECT_ORDERS_USERID_ERR));
//        }
//        return ResponseEntity.ok(response);
//    }


//    @GetMapping("/orders/{orderDate}/{orderStatus}")
//    public ResponseEntity<ApiResponse<List<OrderDto>>> getOrderByDateAndStatus(
//            @PathVariable(value = "orderDate") String orderDate,
//            @PathVariable(value = "orderStatus") String orderStatus) {
//
//        ApiResponse<List<OrderDto>> response = new ApiResponse<>();
//        Map<String, String> messages = props.getMessages();
//        List<OrderDto> ordersByDateAndStatus = orderService.getOrderByDateAndStatus(orderDate, orderStatus);
//        if (ordersByDateAndStatus != null) {
//            response.setStatusCode(200);
//            response.setMessage(messages.get(AppConstants.SELECT_ORDERS_DATE_AND_STATUS));
//            response.setData(ordersByDateAndStatus);
//        } else {
//            response.setStatusCode(500);
//            response.setMessage(messages.get(AppConstants.SELECT_ORDERS_DATE_AND_STATUS_ERR));
//        }
//        return ResponseEntity.ok(response);
//    }

//    @GetMapping("/orders")
//    public ResponseEntity<ApiResponse<List<OrderDto>>> getAllOrders() {
//        ApiResponse<List<OrderDto>> response = new ApiResponse<>();
//        Map<String, String> messages = props.getMessages();
//        List<OrderDto> allOrders = reportService.getAllOrders();
//        if (allOrders != null) {
//            response.setStatusCode(200);
//            response.setMessage(messages.get(AppConstants.FETCHED_ORDERS));
//            response.setData(allOrders);
//        } else {
//            response.setStatusCode(500);
//            response.setMessage(messages.get(AppConstants.FETCHED_ORDERS_ERR));
//        }
//        return ResponseEntity.ok(response);
//    }

}
