package com.kcd.assignment.order.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "order_items")
@EntityListeners(AuditingEntityListener.class)
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;

    private String productName;

    private Long productPrice;

    private int quantity;

    @Comment("상품 총액(옵션 포함)")
    private Long itemAmount;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "orderId")
    private Order order;

    @OneToMany(mappedBy = "orderItem", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    private List<OrderItemOption> orderItemOptions = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Long productPrice) {
        this.productPrice = productPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Long getItemAmount() {
        return itemAmount;
    }

    public void setItemAmount(Long itemAmount) {
        this.itemAmount = itemAmount;
    }
    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public List<OrderItemOption> getOrderItemOptions() {
        return orderItemOptions;
    }

    public void setOrderItemOptions(List<OrderItemOption> orderItemOptions) {
        this.orderItemOptions = orderItemOptions;
    }

    public static class Builder {
        private Long id;
        private Long productId;
        private String productName;
        private Long productPrice;
        private int quantity;
        private Long itemAmount;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder productId(Long productId) {
            this.productId = productId;
            return this;
        }

        public Builder productName(String productName) {
            this.productName = productName;
            return this;
        }

        public Builder productPrice(Long productPrice) {
            this.productPrice = productPrice;
            return this;
        }

        public Builder quantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder itemAmount(Long itemAmount) {
            this.itemAmount = itemAmount;
            return this;
        }

        public OrderItem build() {
            OrderItem orderItem = new OrderItem();
            orderItem.setId(this.id);
            orderItem.setProductId(this.productId);
            orderItem.setProductName(this.productName);
            orderItem.setProductPrice(this.productPrice);
            orderItem.setQuantity(this.quantity);
            orderItem.setItemAmount(this.itemAmount);
            return orderItem;
        }
    }
}
