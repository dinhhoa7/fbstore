package com.hoavd.fbstore.product.repository;

import com.hoavd.fbstore.product.entity.Promotion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long> {
  @Query(value = "select * from promotion p order by p.name desc", nativeQuery = true)
  Page<Promotion> getPageListPromotion(Pageable pageable);

  Promotion findById(long id);

  Promotion findByName(String name);
}
