package com.kcd.assignment.product.model;

import com.kcd.assignment.common.utils.HashIdUtils;
import com.kcd.assignment.product.entity.ProductOption;
import com.kcd.assignment.product.entity.ProductOptionGroup;
import com.kcd.assignment.product.model.validate.ValidSelectCount;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@ValidSelectCount
@Schema(description = "상품 옵션 그룹 생성/수정 요청")
public record ProductOptionGroupRequest(
        @Schema(description = "상품 옵션 그룹 Hash ID")
        String productOptionGroupHid,

        @NotEmpty(message = "상품 옵션 그룹명은 필수값입니다.")
        @Schema(description = "상품 옵션 그룹명", example = "사이즈", requiredMode = Schema.RequiredMode.REQUIRED)
        String productOptionGroupName,

        @Schema(description = "상품 옵션 그룹 순서", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
        int productOptionGroupDisplayOrder,

        @Schema(description = "필수 옵션 여부", example = "false", requiredMode = Schema.RequiredMode.REQUIRED)
        boolean isEssential,

        @Min(value = 1, message = "최소 선택 가능 옵션 수는 1 이상이어야 합니다.")
        @Schema(description = "최소 선택 가능 옵션 수", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
        int minimumSelectCount,

        @Max(value = 3, message = "최대 선택 가능 옵션 수는 3 이하이어야 합니다.")
        @Schema(description = "최대 선택 가능 옵션 수", example = "3", requiredMode = Schema.RequiredMode.REQUIRED)
        int maximumSelectCount,

        @Size(min = 1, message = "상품 옵션 목록은 최소 1개 이상이어야 합니다.")
        @Schema(description = "상품 옵션 목록")
        List<ProductOptionRequest> productOptions
) {

    public ProductOptionGroup toEntity() {
        Long optionGroupId = productOptionGroupHid != null ? HashIdUtils.decodeShort(productOptionGroupHid) : null;
        ProductOptionGroup optionGroup = new ProductOptionGroup.Builder()
                .id(optionGroupId)
                .name(productOptionGroupName)
                .displayOrder(productOptionGroupDisplayOrder)
                .isEssential(isEssential)
                .minimumSelectCount(minimumSelectCount)
                .maximumSelectCount(maximumSelectCount)
                .build();

        // set display order for each product option
        AtomicInteger displayOrder = new AtomicInteger(1);
        List<ProductOption> options = productOptions.stream().map(o -> {
            ProductOption option = o.toEntity();
            option.setProductOptionGroup(optionGroup);
            option.setDisplayOrder(displayOrder.getAndIncrement());
            return option;
        }).toList();

        optionGroup.setProductOptions(options);

        return optionGroup;
    }
}
