package com.kcd.assignment.product.dto;

import com.kcd.assignment.product.entity.ProductCategory;
import com.kcd.assignment.common.utils.HashIdUtils;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "상품 카테고리 DTO")
public record ProductCategoryDto(
        @Schema(description = "상품 카테고리 Hash ID", example = "1a2b3c4d")
        String productCategoryHid,
        @Schema(description = "상품 카테고리 이름", example = "음료", requiredMode = Schema.RequiredMode.REQUIRED)
        String productCategoryName,
        @Schema(description = "상품 카테고리 표시 순서(값이 낮은경우 선순위)", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
        Integer productCategoryDisplayOrder
) {
    public static ProductCategoryDto toDto(ProductCategory productCategory) {
        return new ProductCategoryDto(
                HashIdUtils.encodeShort(productCategory.getId()),
                productCategory.getName(),
                productCategory.getDisplayOrder()
        );
    }
}
