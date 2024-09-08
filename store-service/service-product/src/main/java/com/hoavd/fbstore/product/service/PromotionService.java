package com.hoavd.fbstore.product.service;

import com.hoavd.fbstore.product.entity.Promotion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PromotionService {
  Page<Promotion> getPageList(Pageable pageable);

  Promotion getById(long id);

  Promotion getByName(String name);

  Promotion save(Promotion promotion);

  void delete(long id);
}
