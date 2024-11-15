package com.kcd.assignment.product.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "com.kcd.assignment.product.controller")
public class ProductExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(ProductExceptionHandler.class);

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ProductException.class)
    public ProductErrorResponse handleProductException(ProductException e) {
        log.error("ProductException: {}", e.getMessage());

        return ProductErrorResponse.of(e.getError());
    }
}
