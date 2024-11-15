package com.kcd.assignment.order.model;

import com.kcd.assignment.common.utils.HashIdUtils;
import com.kcd.assignment.order.entity.OrderItemOption;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.PositiveOrZero;

public record OrderOptionRequest(
        @Schema(description = "옵션 그룹 Hash ID", example = "WVZrvZEn")
        String optionGroupHid,

        @Schema(description = "옵션 그룹명", example = "사이즈")
        String optionGroupName,

        @Schema(description = "옵션 Hash ID", example = "WVZrvZEn")
        String optionHid,

        @Schema(description = "옵션명", example = "Tall")
        String optionName,

        @PositiveOrZero(message = "상품 수량은 0 이상이어야 합니다.")
        @Schema(description = "옵션 수량", example = "1")
        int quantity,

        @Schema(description = "옵션 가격", example = "0")
        Long optionPrice
) {
    public OrderItemOption toEntity() {
        Long optionGroupId = optionGroupHid != null ? HashIdUtils.decodeShort(optionGroupHid) : null;
        Long optionId = optionHid != null ? HashIdUtils.decodeShort(optionHid) : null;

        return new OrderItemOption.Builder()
                .optionGroupId(optionGroupId)
                .optionGroupName(optionGroupName)
                .optionId(optionId)
                .optionName(optionName)
                .quantity(quantity)
                .optionPrice(optionPrice)
                .build();
    }
}