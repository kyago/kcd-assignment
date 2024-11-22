package com.kcd.assignment.product.service;

import com.kcd.assignment.product.entity.Product;
import com.kcd.assignment.product.entity.ProductStatus;
import com.kcd.assignment.product.exception.ProductError;
import com.kcd.assignment.product.exception.ProductException;
import com.kcd.assignment.product.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductValidationService {
    private final ProductRepository productRepository;

    public ProductValidationService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    
    public void validateProduct(Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductException(ProductError.PRODUCT_NOT_FOUND));

        if(ProductStatus.SOLD_OUT == product.getStatus()) {
            throw new ProductException(ProductError.PRODUCT_SOLD_OUT);
        }

        if(ProductStatus.DELETED == product.getStatus()) {
            throw new ProductException(ProductError.PRODUCT_DELETED);
        }

        // add other validations here
    }
}
