package com.kcd.assignment.product.dto;

import com.kcd.assignment.common.utils.HashIdUtils;
import com.kcd.assignment.product.entity.Product;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "상품 DTO")
public record ProductDto(
        @Schema(description = "상품 Hash ID", example = "abc123")
        String productHid,
        @Schema(description = "상품명", example = "아메리카노", requiredMode = Schema.RequiredMode.REQUIRED)
        String productName,
        @Schema(description = "상품 가격", example = "4100", requiredMode = Schema.RequiredMode.REQUIRED)
        Long productPrice,
        @Schema(description = "상품 순서", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
        Integer productDisplayOrder,
        ProductCategoryDto productCategory,
        List<ProductOptionGroupDto> productOptionGroups
) {
    public static ProductDto toDto(Product product) {
        return new ProductDto(
                HashIdUtils.encodeShort(product.getId()),
                product.getName(),
                product.getPrice(),
                product.getDisplayOrder(),
                ProductCategoryDto.toDto(product.getProductCategory()),
                product.getProductOptionGroups().stream().map(ProductOptionGroupDto::toDto).toList()
        );
    }
}
