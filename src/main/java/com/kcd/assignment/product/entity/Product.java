package com.kcd.assignment.product.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "products")
@SQLDelete(sql = "UPDATE products SET deleted_at = now(), status = 'DELETED' WHERE id = ?")
@SQLRestriction("deleted_at IS NULL")
@EntityListeners(AuditingEntityListener.class)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("상품명")
    private String name;

    @Comment("상품 설명")
    private String description;

    @Comment("상품 가격")
    private Long price;

    @Comment("표시 순서")
    private int displayOrder;

    @Comment("상품 상태")
    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @Comment("상품 배경색(HEX)")
    private String backgroundColor;

    @Comment("부가세 과세여부(default: 과세)")
    private boolean isTaxable = true;

    private Long categoryId;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "categoryId", insertable = false, updatable = false)
    private ProductCategory productCategory;

    @OneToMany(mappedBy = "product", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    @OrderBy("displayOrder ASC")
    private List<ProductOptionGroup> productOptionGroups = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getPrice() {
        return price;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public int getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public List<ProductOptionGroup> getProductOptionGroups() {
        return productOptionGroups;
    }

    public void setProductOptionGroups(List<ProductOptionGroup> productOptionGroups) {
        this.productOptionGroups = productOptionGroups;
    }

    public static class Builder {
        private Long id;
        private String name;
        private String description;
        private Long price;
        private int displayOrder;
        private ProductStatus status;
        private String backgroundColor;
        private boolean isTaxable;
        private Long categoryId;
        private List<ProductOptionGroup> productOptionGroups;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder price(Long price) {
            this.price = price;
            return this;
        }

        public Builder displayOrder(int displayOrder) {
            this.displayOrder = displayOrder;
            return this;
        }

        public Builder status(ProductStatus status) {
            this.status = status;
            return this;
        }

        public Builder backgroundColor(String backgroundColor) {
            this.backgroundColor = backgroundColor;
            return this;
        }

        public Builder isTaxable(boolean isTaxable) {
            this.isTaxable = isTaxable;
            return this;
        }

        public Builder categoryId(Long categoryId) {
            this.categoryId = categoryId;
            return this;
        }

        public Builder productOptionGroups(List<ProductOptionGroup> productOptionGroups) {
            this.productOptionGroups = productOptionGroups;
            return this;
        }

        public Product build() {
            Product product = new Product();
            product.id = this.id;
            product.name = this.name;
            product.description = this.description;
            product.price = this.price;
            product.displayOrder = this.displayOrder;
            product.status = this.status;
            product.backgroundColor = this.backgroundColor;
            product.isTaxable = this.isTaxable;
            product.categoryId = this.categoryId;
            product.productOptionGroups = this.productOptionGroups;
            return product;
        }
    }
}