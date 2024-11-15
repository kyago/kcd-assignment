package com.kcd.assignment.product.dto;

import com.kcd.assignment.product.entity.ProductOption;
import com.kcd.assignment.common.utils.HashIdUtils;
import io.swagger.v3.oas.annotations.media.Schema;

public record ProductOptionDto(
        @Schema(description = "상품 옵션 Hash ID", example = "abc123")
        String ProductOptionHid,
        @Schema(description = "상품 옵션명", example = "사이즈")
        String ProductOptionName,
        @Schema(description = "상품 옵션 가격", example = "1000")
        Long ProductOptionPrice,
        @Schema(description = "상품 옵션 품절 여부", example = "false")
        Boolean ProductOptionIsRecommended
) {
    public static ProductOptionDto toDto(ProductOption productOption) {
        return new ProductOptionDto(
                HashIdUtils.encodeShort(productOption.getId()),
                productOption.getName(),
                productOption.getPrice(),
                productOption.isRecommended()
        );
    }
}
