package com.kcd.assignment.order.controller;

import com.kcd.assignment.order.dto.OrderDto;
import com.kcd.assignment.order.dto.OrderSimpleDto;
import com.kcd.assignment.order.model.OrderRequest;
import com.kcd.assignment.order.service.OrderService;
import com.kcd.assignment.product.model.IdResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "주문 API")
@RestController
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Operation(summary = "주문 내역 조회")
    @GetMapping("/orders")
    public ResponseEntity<PagedModel<OrderSimpleDto>> getOrders(
            @Parameter(description = "페이지 번호", example = "0") @RequestParam(defaultValue = "0") Integer page,
            @Parameter(description = "페이지 크기", example = "20") @RequestParam(defaultValue = "20") Integer size) {
        return ResponseEntity.ok(orderService.getOrders(PageRequest.of(page, size)));
    }

    @Operation(summary = "주문 상세 조회")
    @GetMapping("/orders/{orderHid}")
    public ResponseEntity<OrderDto> getOrder(@Parameter(description = "주문 Hash ID") @PathVariable String orderHid) {
        return ResponseEntity.ok(orderService.getOrder(orderHid));
    }

    @Operation(summary = "주문 생성")
    @PostMapping("/orders")
    public ResponseEntity<IdResponse> createOrder(@RequestBody @Valid OrderRequest request) {
        return ResponseEntity.ok(new IdResponse(orderService.createOrder(request.toEntity())));
    }

    @Operation(summary = "주문 취소")
    @PostMapping("/orders/{orderHid}/cancel")
    public ResponseEntity<Void> cancelOrder(@PathVariable String orderHid) {
        orderService.cancelOrder(orderHid);
        return ResponseEntity.ok().build();
    }
}
