package com.kcd.assignment.product.repository;

import com.kcd.assignment.product.entity.ProductOptionGroup;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductOptionGroupRepository extends JpaRepository<ProductOptionGroup, Long> {
    @EntityGraph(attributePaths = {"productOptions"})
    List<ProductOptionGroup> findByProductId(Long productId);
}
