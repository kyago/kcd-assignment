package com.kcd.assignment.order.dto;

import com.kcd.assignment.order.entity.OrderStatus;

import java.time.LocalDateTime;
import java.util.List;

public record OrderDto(
        String orderHid,
        String orderNumber,
        Long totalAmount,
        Long discountAmount,
        Long finalAmount,
        OrderStatus status,
        LocalDateTime orderAt,
        List<OrderItemDto> orderItems
) {
    public static OrderDto toDto(com.kcd.assignment.order.entity.Order order) {
        return new OrderDto(
                order.getId().toString(),
                order.getOrderNumber(),
                order.getTotalAmount(),
                order.getDiscountAmount(),
                order.getFinalAmount(),
                order.getStatus(),
                order.getCreatedAt(),
                order.getOrderItems().stream().map(OrderItemDto::toDto).toList()
        );
    }
}
