package com.kcd.assignment.product.service;

import com.kcd.assignment.common.utils.HashIdUtils;
import com.kcd.assignment.product.dto.ProductCategoryDto;
import com.kcd.assignment.product.dto.ProductDto;
import com.kcd.assignment.product.dto.ProductSimpleDto;
import com.kcd.assignment.product.entity.Product;
import com.kcd.assignment.product.entity.ProductCategory;
import com.kcd.assignment.product.entity.ProductOptionGroup;
import com.kcd.assignment.product.exception.ProductError;
import com.kcd.assignment.product.exception.ProductException;
import com.kcd.assignment.product.repository.ProductCategoryRepository;
import com.kcd.assignment.product.repository.ProductOptionGroupRepository;
import com.kcd.assignment.product.repository.ProductOptionRepository;
import com.kcd.assignment.product.repository.ProductRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductCategoryRepository productCategoryRepository;
    private final ProductOptionGroupRepository productOptionGroupRepository;
    private final ProductOptionRepository productOptionRepository;

    public ProductService(ProductRepository productRepository, ProductCategoryRepository productCategoryRepository, ProductOptionGroupRepository productOptionGroupRepository, ProductOptionRepository productOptionRepository) {
        this.productRepository = productRepository;
        this.productCategoryRepository = productCategoryRepository;
        this.productOptionGroupRepository = productOptionGroupRepository;
        this.productOptionRepository = productOptionRepository;
    }

    @Transactional(readOnly = true)
    public PagedModel<ProductCategoryDto> getProductCategories(PageRequest page) {
        page.withSort(Sort.Direction.ASC, "displayOrder");

        return new PagedModel<>(productCategoryRepository.findAll(page)
                .map(ProductCategoryDto::toDto));
    }

    public ProductCategoryDto createProductCategory(String name, Integer displayOrder) {
        if (productCategoryRepository.existsByName(name)) {
            throw new ProductException(ProductError.PRODUCT_CATEGORY_NAME_ALREADY_EXIST);
        }

        ProductCategory productCategory = new ProductCategory();
        productCategory.setName(name);
        productCategory.setDisplayOrder(displayOrder);

        return ProductCategoryDto.toDto(productCategoryRepository.save(productCategory));
    }

    public ProductCategoryDto updateProductCategory(String hid, String name, Integer displayOrder) {
        Long id = HashIdUtils.decodeShort(hid);
        var productCategory = productCategoryRepository.findById(id)
                .orElseThrow(() -> new ProductException(ProductError.PRODUCT_CATEGORY_NOT_FOUND));

        productCategory.setName(name);
        productCategory.setDisplayOrder(displayOrder);

        return ProductCategoryDto.toDto(productCategory);
    }

    public void deleteProductCategory(String productCategoryHid) {
        Long productCategoryId = HashIdUtils.decodeShort(productCategoryHid);
        ProductCategory productCategory = productCategoryRepository.findById(productCategoryId)
                .orElseThrow(() -> new ProductException(ProductError.PRODUCT_CATEGORY_NOT_FOUND));

        if (productRepository.existsByProductCategoryId(productCategory.getId())) {
            throw new ProductException(ProductError.PRODUCT_CATEGORY_CANNOT_DELETE_CAUSE_PRODUCT_EXIST);
        }

        productCategoryRepository.deleteById(productCategoryId);
    }

    @Transactional(readOnly = true)
    public PagedModel<ProductSimpleDto> getProducts(PageRequest page) {
        page.withSort(Sort.Direction.ASC, "displayOrder");
        productRepository.findAll(page);

        return new PagedModel<>(productRepository.findAll(page)
                .map(ProductSimpleDto::toDto));
    }

    @Transactional(readOnly = true)
    public ProductDto getProduct(String productHid) {
        Long productId = HashIdUtils.decodeShort(productHid);

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductException(ProductError.PRODUCT_NOT_FOUND));

        return ProductDto.toDto(product);
    }

    public String createProduct(Product newProduct) {
        if(productRepository.existsByName(newProduct.getName())) {
            throw new ProductException(ProductError.PRODUCT_NAME_ALREADY_EXIST);
        }

        newProduct = productRepository.save(newProduct);

        return HashIdUtils.encodeShort(newProduct.getId());
    }

    public String updateProduct(String productHid, Product targetProduct) {
        Long productId = HashIdUtils.decodeShort(productHid);
        if(productRepository.existsByIdIsNotAndName(productId, targetProduct.getName())) {
            throw new ProductException(ProductError.PRODUCT_NAME_ALREADY_EXIST);
        }

        productRepository.findById(productId)
                .orElseThrow(() -> new ProductException(ProductError.PRODUCT_NOT_FOUND));

        targetProduct.setId(productId);
        productRepository.save(targetProduct);

        return productHid;
    }

    public void deleteProduct(String productHid) {
        Long productId = HashIdUtils.decodeShort(productHid);

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductException(ProductError.PRODUCT_NOT_FOUND));

        List<ProductOptionGroup> optionGroups = productOptionGroupRepository.findByProductId(productId);
        productOptionGroupRepository.deleteAll(optionGroups);

        productRepository.delete(product);
    }
}
