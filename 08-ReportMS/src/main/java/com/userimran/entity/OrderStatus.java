package com.userimran.entity;

public enum OrderStatus {

//    PENDING("pending"),
//    PROCESSED("processed"),
//    DELIVERED("delivered"),
//    CANCELLED("cancelled");
//
//    @JsonCreator
//    public static OrderStatus fromValue(String value) {
//        for (OrderStatus status : OrderStatus.values()) {
//            if (status.status.equalsIgnoreCase(value)) {
//                return status;
//            }
//        }
//        throw new IllegalArgumentException("Invalid order status: " + value);
//    }
//
//    @JsonValue
//    private final String status;

    pending,
    processed,
    delivered,
    cancelled;

}
