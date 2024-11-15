package com.kcd.assignment.order.model;

import com.kcd.assignment.common.utils.HashIdUtils;
import com.kcd.assignment.order.entity.OrderItem;
import com.kcd.assignment.order.entity.OrderItemOption;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.List;

public record OrderItemRequest(
        @Schema(description = "상품 Hash ID", example = "WVZrvZEn")
        String productHid,

        @Schema(description = "상품명", example = "아메리카노")
        String productName,

        @PositiveOrZero(message = "상품 수량은 0 이상이어야 합니다.")
        @Schema(description = "상품 수량", example = "1")
        int quantity,

        @Schema(description = "상품 가격", example = "3000")
        Long productPrice,

        List<@Valid OrderOptionRequest> selectedOptions
) {
    public OrderItem toEntity() {
        Long productId = productHid != null ? HashIdUtils.decodeShort(productHid) : null;
        OrderItem orderItem = new OrderItem.Builder()
                .productId(productId)
                .productName(productName)
                .quantity(quantity)
                .productPrice(productPrice)
                .build();

        if(selectedOptions != null) {
            List<OrderItemOption> options = selectedOptions.stream().map(option -> {
                OrderItemOption orderItemOption = option.toEntity();
                orderItemOption.setOrderItem(orderItem);
                return orderItemOption;
            }).toList();

            orderItem.setOrderItemOptions(options);
            orderItem.setItemAmount( (productPrice + options.stream().mapToLong(option -> option.getOptionPrice() * option.getQuantity()).sum()) * quantity);
        } else {
            orderItem.setItemAmount(productPrice * quantity);
        }

        return orderItem;
    }
}