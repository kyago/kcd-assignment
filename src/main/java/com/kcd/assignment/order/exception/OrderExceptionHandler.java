package com.kcd.assignment.order.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "com.kcd.assignment.order.controller")
public class OrderExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(OrderExceptionHandler.class);

    @ExceptionHandler(OrderException.class)
    public OrderErrorResponse handleOrderException(OrderException e) {
        log.error("OrderException: {}", e.getMessage());

        return OrderErrorResponse.of(e.getOrderError());
    }
}
