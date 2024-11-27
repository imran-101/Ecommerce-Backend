package com.userimran.service;

import com.userimran.dto.OrderDto;
import com.userimran.dto.ReportDto;
import com.userimran.entity.OrderStatus;

import java.time.LocalDate;
import java.util.List;

public interface ReportService {
    List<OrderDto> getAllOrders();
//    List<OrderDto> getAllOrdersByFiltersInPdf(ReportDto reportDto);
//    List<OrderDto> getAllOrdersByFiltersInExcel(ReportDto reportDto);

    List<OrderDto> getOrdersByCriteria(ReportDto reportDto);

    List<OrderDto> getOrdersByExample(ReportDto reportDto);

    List<OrderDto> orderByStatus(OrderStatus orderStatus);
    List<OrderDto> getOrdersBetweenDates(LocalDate startDate, LocalDate endDate);
}
