package com.kcd.assignment.order.dto;

import com.kcd.assignment.common.utils.HashIdUtils;
import com.kcd.assignment.order.entity.OrderItem;

import java.util.List;

public record OrderItemDto(
        String orderItemHid,
        String productHid,
        String productName,
        Long price,
        Integer quantity,
        Long itemAmount,
        List<OrderItemOptionDto> orderItemOptions
) {
    public static OrderItemDto toDto(OrderItem orderItem) {
        return new OrderItemDto(
                orderItem.getId().toString(),
                HashIdUtils.encodeShort(orderItem.getProductId()),
                orderItem.getProductName(),
                orderItem.getProductPrice(),
                orderItem.getQuantity(),
                orderItem.getItemAmount(),
                orderItem.getOrderItemOptions().stream().map(OrderItemOptionDto::toDto).toList()
        );
    }
}
