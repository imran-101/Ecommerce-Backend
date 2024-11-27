package com.userimran.service;

import com.userimran.dto.OrderDto;
import com.userimran.dto.OrderSpecification;
import com.userimran.dto.ReportDto;
import com.userimran.entity.Orders;
import com.userimran.entity.OrderStatus;
import com.userimran.mapper.OrderMapper;
import com.userimran.repository.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

    private final OrdersRepository ordersRepository;


    @Override
    public List<OrderDto> getAllOrders() {
        List<Orders> ordersList = ordersRepository.findAll();
        return ordersList.stream().map(OrderMapper::convertToDto).collect(Collectors.toList());
    }

//    @Override
//    public List<OrderDto> getAllOrdersByFiltersInPdf(ReportDto reportDto) {
//        return List.of();
//    }

//    @Override
//    public List<OrderDto> getAllOrdersByFiltersInExcel(ReportDto reportDto) {
//        List<OrderDto> orders = new ArrayList<>();
//        if (reportDto.getOrderStatus() != null) {
//            orders = orderByStatus(reportDto.getOrderStatus());
//        }
//        if (reportDto.getStartDate() != null || reportDto.getEndDate() != null) {
//            List<OrderDto> dateFilteredOrders = getOrdersBetweenDates(reportDto.getStartDate(),
//                    reportDto.getEndDate());
//            if (!orders.isEmpty()) {
//                orders.retainAll(dateFilteredOrders);
//            } else {
//                orders = dateFilteredOrders;
//            }
//        }
//        if (reportDto.getOrderStatus() == null && reportDto.getStartDate() == null && reportDto.getEndDate() == null) {
//            orders = getAllOrders();
//        }


//        Example example = Example.of(reportDto);
//        List filterList = orderRepository.findAll(example);
//        return filterList.stream().map(OrderMapper::convertToDto).collect(Collectors.toList());


//        return List.of();

//    }


    @Override
    public List<OrderDto> getOrdersByCriteria(ReportDto reportDto){
        Specification<Orders> orderSpecification = OrderSpecification.orderSpecificationByCriteria(reportDto);
        List<Orders> orders = ordersRepository.findAll(orderSpecification);
        return orders.stream().map(OrderMapper::convertToDto).collect(Collectors.toList());
    }


    @Override
    public List<OrderDto> getOrdersByExample(ReportDto reportDto) {
        Orders ordersExample = new Orders();

        if (reportDto.getOrderStatus() != null) {
            ordersExample.setOrderStatus(reportDto.getOrderStatus());
        }

            ExampleMatcher matcher = ExampleMatcher.matching().withIgnoreNullValues();

            Example<Orders> example = Example.of(ordersExample, matcher);

            List<Orders> orders = ordersRepository.findAll(example);

            if (reportDto.getStartDate() != null && reportDto.getEndDate() != null) {
                orders = orders.stream().filter(order -> !order.getOrderDate().isBefore(reportDto.getStartDate()) &&
                        !order.getOrderDate().isAfter(reportDto.getEndDate())).toList();
            }
            return orders.stream().map(OrderMapper::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> orderByStatus(OrderStatus orderStatus) {
        List<Orders> byOrdersStatuses = ordersRepository.findByOrderStatus(orderStatus);
        return byOrdersStatuses.stream().map(OrderMapper::convertToDto).collect(Collectors.toList());
    }

    @Override
    public List<OrderDto> getOrdersBetweenDates(LocalDate startDate, LocalDate endDate) {
        List<Orders> ordersBetweenDate = ordersRepository.findByOrderDateBetween(startDate, endDate);
        return ordersBetweenDate.stream().map(OrderMapper::convertToDto).collect(Collectors.toList());
    }
}
