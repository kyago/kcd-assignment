package com.kcd.assignment.order.exception;

public record OrderErrorResponse(
        String code,
        String message
) {
    public static OrderErrorResponse of(OrderError orderError) {
        return new OrderErrorResponse(orderError.getCode(), orderError.getMessage());
    }
}
