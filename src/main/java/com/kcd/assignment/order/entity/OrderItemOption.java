package com.kcd.assignment.order.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "order_item_options")
@EntityListeners(AuditingEntityListener.class)
public class OrderItemOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long optionGroupId;

    private String optionGroupName;

    private Long optionId;

    private String optionName;

    private Long optionPrice;

    private int quantity;

    @CreatedDate
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "orderItemId")
    private OrderItem orderItem;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOptionName() {
        return optionName;
    }

    public Long getOptionGroupId() {
        return optionGroupId;
    }

    public void setOptionGroupId(Long optionGroupId) {
        this.optionGroupId = optionGroupId;
    }

    public String getOptionGroupName() {
        return optionGroupName;
    }

    public void setOptionGroupName(String optionGroupName) {
        this.optionGroupName = optionGroupName;
    }

    public Long getOptionId() {
        return optionId;
    }

    public void setOptionId(Long optionId) {
        this.optionId = optionId;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public Long getOptionPrice() {
        return optionPrice;
    }

    public void setOptionPrice(Long optionPrice) {
        this.optionPrice = optionPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public OrderItem getOrderItem() {
        return orderItem;
    }

    public void setOrderItem(OrderItem orderItem) {
        this.orderItem = orderItem;
    }

    public static class Builder {
        private Long id;
        private Long optionGroupId;
        private String optionGroupName;
        private Long optionId;
        private String optionName;
        private Long optionPrice;
        private int quantity;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder optionGroupId(Long optionGroupId) {
            this.optionGroupId = optionGroupId;
            return this;
        }

        public Builder optionGroupName(String optionGroupName) {
            this.optionGroupName = optionGroupName;
            return this;
        }

        public Builder optionId(Long optionId) {
            this.optionId = optionId;
            return this;
        }

        public Builder optionName(String optionName) {
            this.optionName = optionName;
            return this;
        }

        public Builder optionPrice(Long optionPrice) {
            this.optionPrice = optionPrice;
            return this;
        }

        public Builder quantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public OrderItemOption build() {
            OrderItemOption orderItemOption = new OrderItemOption();
            orderItemOption.setId(this.id);
            orderItemOption.setOptionGroupId(this.optionGroupId);
            orderItemOption.setOptionGroupName(this.optionGroupName);
            orderItemOption.setOptionId(this.optionId);
            orderItemOption.setOptionName(this.optionName);
            orderItemOption.setOptionPrice(this.optionPrice);
            orderItemOption.setQuantity(this.quantity);
            return orderItemOption;
        }
    }
}
