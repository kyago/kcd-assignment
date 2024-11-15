package com.kcd.assignment.product.controller;

import com.kcd.assignment.product.dto.ProductCategoryDto;
import com.kcd.assignment.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "상품 카테고리 API")
@RestController
public class ProductCategoryController {

    private final ProductService productService;

    public ProductCategoryController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "상품 카테고리 목록 조회")
    @GetMapping("/products/categories")
    public ResponseEntity<PagedModel<ProductCategoryDto>> getProductCategories(
            @Parameter(description = "페이지 번호", example = "0") @RequestParam(defaultValue = "0") Integer page,
            @Parameter(description = "페이지 크기", example = "20") @RequestParam(defaultValue = "20") Integer size) {
        return ResponseEntity.ok(productService.getProductCategories(PageRequest.of(page, size)));
    }

    @Operation(summary = "상품 카테고리 생성")
    @PostMapping("/products/categories")
    public ResponseEntity<ProductCategoryDto> createProductCategory(
            @Parameter(description = "카테고리명") @RequestParam String name,
            @Parameter(description = "카테고리 순서(낮은값 우선)") @RequestParam Integer displayOrder) {
        return ResponseEntity.ok(productService.createProductCategory(name, displayOrder));
    }

    @Operation(summary = "상품 카테고리 수정")
    @PutMapping("/products/categories/{productCategoryHid}")
    public ResponseEntity<ProductCategoryDto> updateProductCategory(
            @Parameter(description = "카테고리 Hash ID") @PathVariable String productCategoryHid,
            @Parameter(description = "카테고리명") @RequestParam String name,
            @Parameter(description = "카테고리 순서(낮은값 우선)") @RequestParam Integer displayOrder) {
        return ResponseEntity.ok(productService.updateProductCategory(productCategoryHid, name, displayOrder));
    }

    @Operation(summary = "상품 카테고리 삭제")
    @DeleteMapping("/products/categories/{productCategoryHid}")
    public ResponseEntity<Void> deleteProductCategory(@Parameter(description = "카테고리 Hash ID") @PathVariable String productCategoryHid) {
        productService.deleteProductCategory(productCategoryHid);
        return ResponseEntity.ok().build();
    }
}
