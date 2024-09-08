package com.hoavd.fbstore.product.service;

import com.hoavd.fbstore.product.entity.PromotionProduct;

public interface PromotionProductService {
  PromotionProduct getById(long id);

  PromotionProduct save(PromotionProduct promotionProduct);

  void delete(long id);
}
