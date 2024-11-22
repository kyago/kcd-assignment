package com.kcd.assignment.product.exception;

public enum ProductError {
    PRODUCT_NOT_FOUND("PRODUCT-001", "해당 상품을 찾을 수 없습니다."),
    PRODUCT_NAME_ALREADY_EXIST("PRODUCT-002", "이미 존재하는 상품명입니다."),
    PRODUCT_OPTION_GROUP_MINIMUM_SELECT_COUNT_GREATER_THAN_MAXIMUM_SELECT_COUNT("PRODUCT-003", "옵션 그룹의 최소 선택 개수가 최대 선택 개수보다 큽니다."),
    PRODUCT_SOLD_OUT("PRODUCT-004", "해당 상품은 품절되었습니다."),
    PRODUCT_DELETED("PRODUCT-005", "해당 상품은 삭제되었습니다."),


    PRODUCT_CATEGORY_NOT_FOUND("PRODUCT_CATEGORY-001", "해당 카테고리를 찾을 수 없습니다."),
    PRODUCT_CATEGORY_NAME_ALREADY_EXIST("PRODUCT_CATEGORY-002", "이미 존재하는 카테고리입니다."),
    PRODUCT_CATEGORY_CANNOT_DELETE_CAUSE_PRODUCT_EXIST("PRODUCT_CATEGORY-003", "해당 카테고리에 상품이 존재하여 카테고리를 삭제할 수 없습니다. 해당 카테고리의 상품을 모두 삭제한 후 다시 시도해주세요.")
    ;

    private final String code;
    private final String message;

    ProductError(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
