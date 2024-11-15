package com.kcd.assignment.product.repository;

import com.kcd.assignment.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsByName(String name);
    boolean existsByIdIsNotAndName(Long id, String name);
    boolean existsByProductCategoryId(Long productCategoryId);
}
