package com.kcd.assignment.product.dto;

import com.kcd.assignment.common.utils.HashIdUtils;
import com.kcd.assignment.product.entity.Product;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "상품 목록용 DTO")
public record ProductSimpleDto(
        @Schema(description = "상품 Hash ID", example = "abc123")
        String productHid,
        @Schema(description = "상품명", example = "상품1")
        String productName,
        @Schema(description = "상품 가격", example = "10000")
        Long productPrice,
        @Schema(description = "상품 순서", example = "1")
        Integer productDisplayOrder,
        ProductCategoryDto productCategory
) {
    public static ProductSimpleDto toDto(Product product) {
        return new ProductSimpleDto(
                HashIdUtils.encodeShort(product.getId()),
                product.getName(),
                product.getPrice(),
                product.getDisplayOrder(),
                ProductCategoryDto.toDto(product.getProductCategory())
        );
    }
}
