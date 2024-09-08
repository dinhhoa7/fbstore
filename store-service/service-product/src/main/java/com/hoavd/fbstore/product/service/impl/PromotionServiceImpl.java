package com.hoavd.fbstore.product.service.impl;

import com.hoavd.fbstore.product.entity.Promotion;
import com.hoavd.fbstore.product.repository.PromotionRepository;
import com.hoavd.fbstore.product.service.PromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PromotionServiceImpl implements PromotionService {
  @Autowired
  private PromotionRepository promotionRepository;

  @Override
  public Page<Promotion> getPageList(Pageable pageable) {
    return promotionRepository.getPageListPromotion(pageable);
  }

  @Override
  public Promotion getById(long id) {
    return promotionRepository.findById(id);
  }

  @Override
  public Promotion getByName(String name) {
    return promotionRepository.findByName(name);
  }

  @Override
  public Promotion save(Promotion promotion) {
    return promotionRepository.save(promotion);
  }

  @Override
  public void delete(long id) {
    promotionRepository.deleteById(id);
  }
}
