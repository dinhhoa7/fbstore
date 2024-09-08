package com.hoavd.fbstore.product.service.impl;

import com.hoavd.fbstore.product.entity.PromotionProduct;
import com.hoavd.fbstore.product.repository.PromotionProductRepository;
import com.hoavd.fbstore.product.service.PromotionProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PromotionProductServiceImpl implements PromotionProductService {
  @Autowired
  private PromotionProductRepository promotionProductRepository;

  @Override
  public PromotionProduct getById(long id) {
    return promotionProductRepository.findById(id);
  }

  @Override
  public PromotionProduct save(PromotionProduct promotionProduct) {
    return promotionProductRepository.save(promotionProduct);
  }

  @Override
  public void delete(long id) {
    promotionProductRepository.deleteById(id);
  }
}
