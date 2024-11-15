package com.kcd.assignment.product.controller;

import com.kcd.assignment.product.dto.ProductDto;
import com.kcd.assignment.product.dto.ProductSimpleDto;
import com.kcd.assignment.product.exception.ProductError;
import com.kcd.assignment.product.exception.ProductException;
import com.kcd.assignment.product.model.IdResponse;
import com.kcd.assignment.product.model.ProductRequest;
import com.kcd.assignment.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "상품 API")
@RestController
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "상품 목록 조회")
    @GetMapping("/products")
    public ResponseEntity<PagedModel<ProductSimpleDto>> getProducts(
            @Parameter(description = "페이지 번호", example = "0") @RequestParam(defaultValue = "0") Integer page,
            @Parameter(description = "페이지 크기", example = "20") @RequestParam(defaultValue = "20") Integer size) {
        return ResponseEntity.ok(productService.getProducts(PageRequest.of(page, size)));
    }

    @Operation(summary = "상품 상세 조회")
    @GetMapping("/products/{productHid}")
    public ResponseEntity<ProductDto> getProduct(
            @Parameter(description = "상품 Hash ID") @PathVariable String productHid) {
        return ResponseEntity.ok(productService.getProduct(productHid));
    }

    @Operation(summary = "상품 등록")
    @PostMapping("/products")
    public ResponseEntity<IdResponse> createProduct(@RequestBody @Valid ProductRequest request) {
        return ResponseEntity.ok(new IdResponse(productService.createProduct(request.toEntity())));
    }

    @Operation(summary = "상품 수정")
    @PutMapping("/products/{productHid}")
    public ResponseEntity<IdResponse> updateProduct(
            @Parameter(description = "상품 Hash ID") @PathVariable String productHid,
            @RequestBody @Valid ProductRequest request) {
        return ResponseEntity.ok(new IdResponse(productService.updateProduct(productHid, request.toEntity())));
    }

    @Operation(summary = "상품 삭제(Soft delete)")
    @DeleteMapping("/products/{productHid}")
    public ResponseEntity<Void> deleteProduct(@Parameter(description = "상품 Hash ID") @PathVariable String productHid) {
        productService.deleteProduct(productHid);
        return ResponseEntity.ok().build();
    }
}
