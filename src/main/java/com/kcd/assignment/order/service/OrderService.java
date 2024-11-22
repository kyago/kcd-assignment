package com.kcd.assignment.order.service;

import com.kcd.assignment.common.utils.HashIdUtils;
import com.kcd.assignment.order.dto.OrderDto;
import com.kcd.assignment.order.dto.OrderSimpleDto;
import com.kcd.assignment.order.entity.Order;
import com.kcd.assignment.order.exception.OrderError;
import com.kcd.assignment.order.exception.OrderException;
import com.kcd.assignment.order.repository.OrderRepository;
import com.kcd.assignment.product.service.ProductValidationService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.web.PagedModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductValidationService productValidationService;

    public OrderService(OrderRepository orderRepository, ProductValidationService productValidationService) {
        this.orderRepository = orderRepository;
        this.productValidationService = productValidationService;
    }

    @Transactional(readOnly = true)
    public PagedModel<OrderSimpleDto> getOrders(PageRequest pageRequest) {
        return new PagedModel<>(orderRepository.findAll(pageRequest)
                .map(OrderSimpleDto::toDto));
    }

    @Transactional(readOnly = true)
    public OrderDto getOrder(String orderHid) {
        Long orderId = HashIdUtils.decodeShort(orderHid);
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderException(OrderError.ORDER_NOT_FOUND));

        return OrderDto.toDto(order);
    }

    public String createOrder(Order newOrder) {
        newOrder.getOrderItems().forEach(orderItem -> {
            productValidationService.validateProduct(orderItem.getProductId());
        });

        Order order = orderRepository.save(newOrder);

        return HashIdUtils.encodeShort(order.getId());
    }

    public void cancelOrder(String orderHid) {
        Long orderId = HashIdUtils.decodeShort(orderHid);
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderException(OrderError.ORDER_NOT_FOUND));

        order.cancel();
    }
}
