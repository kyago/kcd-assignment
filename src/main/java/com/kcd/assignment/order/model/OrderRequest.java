package com.kcd.assignment.order.model;

import com.kcd.assignment.common.utils.HashIdUtils;
import com.kcd.assignment.order.entity.Order;
import com.kcd.assignment.order.entity.OrderItem;
import com.kcd.assignment.order.entity.OrderItemOption;
import com.kcd.assignment.order.entity.OrderStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.util.List;

@Schema(description = "주문 요청")
public record OrderRequest(
        @Size(min = 1, message = "주문 상품은 최소 1개 이상이어야 합니다.")
        @NotNull(message = "주문할 상품이 포함되어야 합니다.")
        List<@Valid OrderItemRequest> orderItems,

        // 퍼센트 할인의 경우 프론트엔드에서 계산된 금액을 전달받아 사용
        @Schema(description = "할인 금액", example = "0")
        Long discountAmount
) {
    public Order toEntity() {
        Order order = new Order.Builder()
                .status(OrderStatus.PENDING)
                .discountAmount(discountAmount)
                .build();

        List<OrderItem> items = orderItems.stream().map(item -> {
            OrderItem orderItem = item.toEntity();
            orderItem.setOrder(order);
            return orderItem;
        }).toList();

        order.setOrderItems(items);
        order.setTotalAmount(items.stream().mapToLong(OrderItem::getItemAmount).sum());
        order.setFinalAmount(order.getTotalAmount() - discountAmount);

        return order;
    }
}
