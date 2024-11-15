package com.kcd.assignment.order.dto;

import com.kcd.assignment.common.utils.HashIdUtils;
import com.kcd.assignment.order.entity.Order;
import com.kcd.assignment.order.entity.OrderStatus;

import java.time.LocalDateTime;

public record OrderSimpleDto(
        String orderHid,
        String orderNumber,
        Long totalAmount,
        Long discountAmount,
        Long finalAmount,
        OrderStatus status,
        LocalDateTime orderAt
) {

    public static OrderSimpleDto toDto(Order order) {
        return new OrderSimpleDto(
                HashIdUtils.encodeShort(order.getId()),
                order.getOrderNumber(),
                order.getTotalAmount(),
                order.getDiscountAmount(),
                order.getFinalAmount(),
                order.getStatus(),
                order.getCreatedAt()
        );
    }
}
