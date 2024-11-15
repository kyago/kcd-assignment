package com.kcd.assignment.product.exception;

public class ProductException extends RuntimeException {

    private final ProductError error;

    public ProductException(ProductError productError) {
        super(productError.getMessage());
        this.error = productError;
    }

    public ProductError getError() {
        return error;
    }
}
