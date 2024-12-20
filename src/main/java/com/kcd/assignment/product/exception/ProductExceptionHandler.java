package com.kcd.assignment.product.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "com.kcd.assignment.product.controller")
public class ProductExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(ProductExceptionHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProductErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        
        // TODO : FieldError를 이용하여 ProductError를 생성하고 ProductErrorResponse로 변환하여 반환

        return null;
    }

    @ExceptionHandler(ProductException.class)
    public ResponseEntity<ProductErrorResponse> handleProductException(ProductException e) {
        log.error("ProductException: {}", e.getMessage());

        return ResponseEntity.badRequest().body(ProductErrorResponse.of(e.getError()));
    }
}
