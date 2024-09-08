package com.hoavd.fbstore.admin.service.promotion.impl;

import com.hoavd.fbstore.admin.model.request.PromotionRequest;
import com.hoavd.fbstore.admin.model.request.PromotionUpdateRequest;
import com.hoavd.fbstore.admin.service.promotion.AdminPromotionService;
import com.hoavd.fbstore.common.constants.ResponseMessageConstants;
import com.hoavd.fbstore.common.enums.Enums;
import com.hoavd.fbstore.common.exception.BusinessException;
import com.hoavd.fbstore.common.model.Pagination;
import com.hoavd.fbstore.common.model.ResponseDataPagination;
import com.hoavd.fbstore.product.entity.Category;
import com.hoavd.fbstore.product.entity.Promotion;
import com.hoavd.fbstore.product.service.PromotionService;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminPromotionServiceImpl implements AdminPromotionService {
  private static final Logger logger = LoggerFactory.getLogger(AdminPromotionServiceImpl.class);

  @Autowired
  private PromotionService promotionService;

  @Override
  public Promotion createPromotion(PromotionRequest request) throws Exception {
    Promotion promotionName = promotionService.getByName(request.getName());
    if (promotionName != null)
      throw new BusinessException(ResponseMessageConstants.PROMOTION_NAME_ALREADY_EXIST);
    Promotion promotion = new Promotion();
    promotion.setName(request.getName());
    promotion.setStartDate(request.getStartDate());
    promotion.setEndDate(request.getEndDate());
    promotion.setStatus(request.isStatus());
    promotion.setExpired(request.isExpired());
    promotion.setDescription(request.getDescription());
    promotion.create();
    return promotionService.save(promotion);
  }

  @Override
  public Promotion updatePromotion(PromotionUpdateRequest request) throws Exception {
    logger.info(request.toString());
    Promotion promotion = promotionService.getById(request.getId());
    if (promotion == null)
      throw new BusinessException(ResponseMessageConstants.PROMOTION_DOES_NOT_EXIST);
    if (Strings.isBlank(request.getName())){
      throw new BusinessException(ResponseMessageConstants.INFORMATION_INVALID);
    }
    promotion.setName(request.getName());
    promotion.setStartDate(request.getStartDate());
    promotion.setEndDate(request.getEndDate());
    promotion.setStatus(request.isStatus());
    promotion.setExpired(request.isExpired());
    promotion.setDescription(request.getDescription());
    promotion.update();
    return promotionService.save(promotion);
  }

  @Override
  public void deletePromotion(long id) throws Exception {
    Promotion promotion = promotionService.getById(id);
    if (promotion == null)
      throw new BusinessException(ResponseMessageConstants.PROMOTION_DOES_NOT_EXIST);
    promotionService.delete(id);
  }

  @Override
  public ResponseDataPagination getPageListPromotion(int page, int size) throws Exception {
    ResponseDataPagination responseDataPagination = new ResponseDataPagination();
    int pageReq = page >= 1 ? page - 1 : page;
    Pageable pageable = PageRequest.of(pageReq, size);
    Page<Promotion> promotionPage = promotionService.getPageList(pageable);
    List<Promotion> promotionList = promotionPage.getContent();
    responseDataPagination.setData(promotionList);
    Pagination pagination = new Pagination();
    pagination.setCurrentPage(page);
    pagination.setPageSize(size);
    pagination.setTotalPage(promotionPage.getTotalPages());
    pagination.setTotalRecords(promotionPage.getTotalElements());
    responseDataPagination.setStatus(Enums.ResponseStatus.SUCCESS.getStatus());
    responseDataPagination.setPagination(pagination);
    return responseDataPagination;
  }
}
