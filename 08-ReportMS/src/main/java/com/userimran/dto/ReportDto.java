package com.userimran.dto;

import com.userimran.entity.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ReportDto {
    private OrderStatus orderStatus;
    private LocalDate startDate;
    private LocalDate endDate;
}
