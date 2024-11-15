package com.kcd.assignment.product.dto;

import com.kcd.assignment.common.utils.HashIdUtils;
import com.kcd.assignment.product.entity.ProductOptionGroup;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record ProductOptionGroupDto(
        @Schema(description = "상품 옵션 그룹 Hash ID", example = "abc123")
        String ProductOptionGroupHid,
        @Schema(description = "상품 옵션 그룹명", example = "사이즈")
        String ProductOptionGroupName,
        @Schema(description = "상품 옵션 목록")
        List<ProductOptionDto> ProductOptions,
        @Schema(description = "필수 옵션 여부", example = "false")
        boolean isEssential,
        @Schema(description = "최소 선택 가능 옵션 수", example = "1")
        int minimumSelectCount,
        @Schema(description = "최대 선택 가능 옵션 수", example = "1")
        int maximumSelectCount
) {
    public static ProductOptionGroupDto toDto(ProductOptionGroup productOptionGroup) {
        return new ProductOptionGroupDto(
                HashIdUtils.encodeShort(productOptionGroup.getId()),
                productOptionGroup.getName(),
                productOptionGroup.getProductOptions().stream().map(ProductOptionDto::toDto).toList(),
                productOptionGroup.isEssential(),
                productOptionGroup.getMinimumSelectCount(),
                productOptionGroup.getMaximumSelectCount()
        );
    }
}
