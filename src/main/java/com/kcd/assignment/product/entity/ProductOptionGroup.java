package com.kcd.assignment.product.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product_option_groups")
@SQLDelete(sql = "UPDATE product_option_groups SET deleted_at = NOW() WHERE id = ?")
@SQLRestriction("deleted_at IS NULL")
@EntityListeners(AuditingEntityListener.class)
public class ProductOptionGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("옵션 그룹명")
    private String name;

    @Comment("노출 순서")
    private int displayOrder;

    @Comment("필수 여부")
    private boolean isEssential;

    @Comment("최소 선택 개수")
    private int minimumSelectCount;

    @Comment("최대 선택 개수")
    private int maximumSelectCount;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    private LocalDateTime deletedAt;

    @OneToMany(mappedBy = "productOptionGroup", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, orphanRemoval = true)
    @OrderBy("displayOrder ASC")
    private List<ProductOption> productOptions = new ArrayList<>();

    @ManyToOne
    @JoinColumn(referencedColumnName = "id", name = "productId")
    private Product product;

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

    public int getDisplayOrder() {
        return displayOrder;
    }

    public boolean isEssential() {
        return isEssential;
    }

    public void setEssential(boolean essential) {
        isEssential = essential;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getMinimumSelectCount() {
        return minimumSelectCount;
    }

    public void setMinimumSelectCount(int minimumSelectCount) {
        this.minimumSelectCount = minimumSelectCount;
    }

    public int getMaximumSelectCount() {
        return maximumSelectCount;
    }

    public void setMaximumSelectCount(int maximumSelectCount) {
        this.maximumSelectCount = maximumSelectCount;
    }

    public void setDisplayOrder(int displayOrder) {
        this.displayOrder = displayOrder;
    }

    public List<ProductOption> getProductOptions() {
        return productOptions;
    }

    public Product getProduct() {
        return product;
    }

    public void addOption(ProductOption option) {
        this.productOptions.add(option);
        option.setProductOptionGroup(this); // 양방향 관계 설정
    }

    public void setProductOptions(List<ProductOption> productOptions) {
        this.productOptions.clear();
        productOptions.forEach(this::addOption);
    }

    public static class Builder {
        private Long id;
        private String name;
        private int displayOrder;
        private boolean isEssential;
        private int minimumSelectCount;
        private int maximumSelectCount;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder displayOrder(int displayOrder) {
            this.displayOrder = displayOrder;
            return this;
        }

        public Builder isEssential(boolean isEssential) {
            this.isEssential = isEssential;
            return this;
        }

        public Builder minimumSelectCount(int minimumSelectCount) {
            this.minimumSelectCount = minimumSelectCount;
            return this;
        }

        public Builder maximumSelectCount(int maximumSelectCount) {
            this.maximumSelectCount = maximumSelectCount;
            return this;
        }

        public ProductOptionGroup build() {
            ProductOptionGroup productOptionGroup = new ProductOptionGroup();
            productOptionGroup.setId(id);
            productOptionGroup.setName(name);
            productOptionGroup.setDisplayOrder(displayOrder);
            productOptionGroup.setEssential(isEssential);
            productOptionGroup.setMinimumSelectCount(minimumSelectCount);
            productOptionGroup.setMaximumSelectCount(maximumSelectCount);
            return productOptionGroup;
        }
    }
}
