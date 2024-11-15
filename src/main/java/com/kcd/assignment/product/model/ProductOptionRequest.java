package com.kcd.assignment.product.model;

import com.kcd.assignment.common.utils.HashIdUtils;
import com.kcd.assignment.product.entity.ProductOption;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;

@Schema(description = "상품 옵션 생성/수정 요청")
public record ProductOptionRequest(
        @Schema(description = "상품 옵션 Hash ID")
        String productOptionHid,

        @NotEmpty(message = "상품 옵션명은 필수값입니다.")
        @Schema(description = "상품 옵션명", example = "Tall", requiredMode = Schema.RequiredMode.REQUIRED)
        String productOptionName,

        @PositiveOrZero(message = "상품 옵션 가격은 0 이상의 값이어야 합니다.")
        @Schema(description = "상품 옵션 가격", example = "0", requiredMode = Schema.RequiredMode.REQUIRED)
        Long productOptionPrice,

        @Schema(description = "추천 옵션 여부", example = "false")
        boolean isRecommended
) {

        public ProductOption toEntity() {
                Long optionId = productOptionHid != null ? HashIdUtils.decodeShort(productOptionHid) : null;
                return new ProductOption.Builder()
                        .id(optionId)
                        .name(productOptionName)
                        .price(productOptionPrice)
                        .isRecommended(isRecommended)
                        .build();
        }
}
