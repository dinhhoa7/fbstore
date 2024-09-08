package com.hoavd.fbstore.admin.service.promotion;

import com.hoavd.fbstore.admin.model.request.PromotionRequest;
import com.hoavd.fbstore.admin.model.request.PromotionUpdateRequest;
import com.hoavd.fbstore.common.model.ResponseDataPagination;
import com.hoavd.fbstore.product.entity.Promotion;

public interface AdminPromotionService {
  Promotion createPromotion(PromotionRequest request) throws Exception;

  Promotion updatePromotion(PromotionUpdateRequest request) throws Exception;

  void deletePromotion(long id) throws Exception;

  ResponseDataPagination getPageListPromotion(int page, int size) throws Exception;
}
