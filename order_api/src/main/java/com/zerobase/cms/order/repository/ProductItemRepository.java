package com.zerobase.cms.order.repository;

import com.zerobase.cms.order.domain.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductItemRepository extends JpaRepository<Product,Long> {
}
