package com.kcd.assignment.order.exception;

public class OrderException extends RuntimeException {
    private final OrderError orderError;

    public OrderException(OrderError orderError) {
        super(orderError.getMessage());
        this.orderError = orderError;
    }

    public OrderError getOrderError() {
        return orderError;
    }
}
