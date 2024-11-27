package com.userimran.dto;

import com.userimran.entity.Orders;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class OrderSpecification {

    public static Specification<Orders> orderSpecificationByCriteria(ReportDto reportDto) {

        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Add condition for orderStatus if it is not null
            if (reportDto.getOrderStatus() != null) {
                predicates.add(criteriaBuilder.equal(root.get("orderStatus"), reportDto.getOrderStatus()));
            }

            // Add condition for startDate if it is not null
            if (reportDto.getStartDate() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("orderDate"), reportDto.getStartDate()));
            }

            // Add condition for endDate if it is not null
            if (reportDto.getEndDate() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("orderDate"), reportDto.getEndDate()));
            }

            // If no filters are provided, return all data (no conditions)
            if (predicates.isEmpty()) {
                return criteriaBuilder.conjunction(); // This returns all records if no predicates are set
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });

    }
}
