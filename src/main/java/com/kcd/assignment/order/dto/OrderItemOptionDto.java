package com.kcd.assignment.order.dto;

import com.kcd.assignment.common.utils.HashIdUtils;
import com.kcd.assignment.order.entity.OrderItemOption;

public record OrderItemOptionDto(
        String orderItemOptionHid,
        String optionName,
        Long price
) {
    public static OrderItemOptionDto toDto(OrderItemOption orderItemOption) {
        return new OrderItemOptionDto(
                HashIdUtils.encodeShort(orderItemOption.getId()),
                orderItemOption.getOptionGroupName() +
                        " : " +
                        orderItemOption.getOptionName() +
                        " *" +
                        orderItemOption.getQuantity(),
                orderItemOption.getOptionPrice()
        );
    }
}
