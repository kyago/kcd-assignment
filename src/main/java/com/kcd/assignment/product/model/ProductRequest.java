package com.kcd.assignment.product.model;

import com.kcd.assignment.common.utils.HashIdUtils;
import com.kcd.assignment.product.entity.Product;
import com.kcd.assignment.product.entity.ProductOptionGroup;
import com.kcd.assignment.product.entity.ProductStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Schema(description = "상품 생성/수정 요청")
public record ProductRequest(
        @NotEmpty(message = "상품명은 필수값입니다.")
        @Schema(description = "상품명", example = "아메리카노", requiredMode = Schema.RequiredMode.REQUIRED)
        String productName,

        @PositiveOrZero(message = "상품 가격은 0 이상의 값이어야 합니다.")
        @Schema(description = "상품 가격", example = "4100", minimum = "0", requiredMode = Schema.RequiredMode.REQUIRED)
        Long productPrice,

        @Schema(description = "상품 순서", example = "1")
        int productDisplayOrder,

        @NotEmpty(message = "상품 카테고리 Hash ID는 필수값입니다.")
        @Schema(description = "상품 카테고리 Hash ID", example = "m79dazbj")
        String categoryHid,

        @Schema(description = "상품 배경색", example = "#FFFFFF")
        String backgroundColor,

        @Schema(description = "과세 여부", example = "true")
        boolean isTaxable,

        @Schema(description = "상품 옵션 그룹 Hash ID 목록")
        List<@Valid ProductOptionGroupRequest> productOptionGroups
) {
    public Product toEntity() {
        Product product = new Product.Builder()
                .name(productName)
                .price(productPrice)
                .status(ProductStatus.DISPLAY)
                .displayOrder(productDisplayOrder)
                .categoryId(HashIdUtils.decodeShort(categoryHid))
                .backgroundColor(backgroundColor)
                .isTaxable(isTaxable)
                .build();

        AtomicInteger displayOrder = new AtomicInteger(1);
        List<ProductOptionGroup> optionGroups = productOptionGroups.stream().map(groups -> {
            ProductOptionGroup group = groups.toEntity();
            group.setProduct(product);
            group.setDisplayOrder(displayOrder.getAndIncrement());
            return group;
        }).toList();

        product.setProductOptionGroups(optionGroups);

        return product;
    }
}
