package com.kcd.assignment.product.model.validate;

import com.kcd.assignment.product.model.ProductOptionGroupRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SelectCountValidator implements ConstraintValidator<ValidSelectCount, ProductOptionGroupRequest> {
    @Override
    public boolean isValid(ProductOptionGroupRequest request, ConstraintValidatorContext context) {
        return request.maximumSelectCount() >= request.minimumSelectCount();
    }
}
