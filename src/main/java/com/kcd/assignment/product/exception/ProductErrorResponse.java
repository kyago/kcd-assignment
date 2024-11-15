package com.kcd.assignment.product.exception;

public record ProductErrorResponse(
        String code,
        String message
) {

    public static ProductErrorResponse of(ProductError productError) {
        return new ProductErrorResponse(productError.getCode(), productError.getMessage());
    }
}
