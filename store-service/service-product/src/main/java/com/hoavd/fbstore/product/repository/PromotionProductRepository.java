package com.hoavd.fbstore.product.repository;

import com.hoavd.fbstore.product.entity.PromotionProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionProductRepository extends JpaRepository<PromotionProduct, Long> {
  PromotionProduct findById(long id);
}
