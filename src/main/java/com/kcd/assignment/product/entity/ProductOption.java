package com.kcd.assignment.product.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "product_options")
@SQLDelete(sql = "UPDATE product_options SET deleted_at = NOW() WHERE id = ?")
@SQLRestriction("deleted_at IS NULL")
@EntityListeners(AuditingEntityListener.class)
public class ProductOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long price;

    private int displayOrder;

    @Comment("추천 여부")
    private boolean isRecommended;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "productOptionGroupId")
    private ProductOptionGroup productOptionGroup;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Long getPrice() {
        return price;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }

    public void setProductOptionGroup(ProductOptionGroup productOptionGroup) {
        this.productOptionGroup = productOptionGroup;
    }

    public ProductOptionGroup getProductOptionGroup() {
        return productOptionGroup;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }

    public boolean isRecommended() {
        return isRecommended;
    }

    public void setRecommended(boolean recommended) {
        isRecommended = recommended;
    }

    public static class Builder {
        private Long id;
        private String name;
        private Long price;
        private boolean isRecommended;
        private ProductOptionGroup productOptionGroup;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder price(Long price) {
            this.price = price;
            return this;
        }

        public Builder isRecommended(boolean isRecommended) {
            this.isRecommended = isRecommended;
            return this;
        }

        public Builder productOptionGroupId(ProductOptionGroup productOptionGroup) {
            this.productOptionGroup = productOptionGroup;
            return this;
        }

        public ProductOption build() {
            ProductOption productOption = new ProductOption();
            productOption.id = this.id;
            productOption.name = this.name;
            productOption.price = this.price;
            productOption.isRecommended = this.isRecommended;
            productOption.productOptionGroup = this.productOptionGroup;
            return productOption;
        }
    }
}
