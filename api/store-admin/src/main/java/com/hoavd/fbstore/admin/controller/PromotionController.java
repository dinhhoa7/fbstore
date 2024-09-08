package com.hoavd.fbstore.admin.controller;

import com.hoavd.fbstore.admin.model.request.CategoryRequest;
import com.hoavd.fbstore.admin.model.request.CategoryUpdateRequest;
import com.hoavd.fbstore.admin.model.request.PromotionRequest;
import com.hoavd.fbstore.admin.model.request.PromotionUpdateRequest;
import com.hoavd.fbstore.admin.service.category.AdminCategoryService;
import com.hoavd.fbstore.admin.service.promotion.AdminPromotionService;
import com.hoavd.fbstore.common.constants.PagingConstants;
import com.hoavd.fbstore.common.constants.ResponseMessageConstants;
import com.hoavd.fbstore.common.enums.Enums;
import com.hoavd.fbstore.common.exception.BusinessException;
import com.hoavd.fbstore.common.model.ResponseData;
import com.hoavd.fbstore.common.utils.LogUtils;
import com.hoavd.fbstore.product.entity.Promotion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/promotion")
public class PromotionController {
  private static final Logger logger = LoggerFactory.getLogger(PromotionController.class);

  @Autowired
  private AdminPromotionService adminPromotionService;

  @GetMapping("/get-list")
  public ResponseData getListPromotion(@RequestParam(value = "page", required = false, defaultValue = PagingConstants.PAGING_STRING_PAGE_DEFAULT) int page,
    @RequestParam(value = "size", required = false, defaultValue = PagingConstants.PAGING_STRING_SIZE_DEFAULT) int size) {
    ResponseData responseData = new ResponseData();
    try {
      return adminPromotionService.getPageListPromotion(page, size);
    } catch (BusinessException be) {
      logger.error(LogUtils.printLogStackTrace(be));
      responseData.setStatus(Enums.ResponseStatus.ERROR);
      responseData.setMessage(be.getMessage());
    } catch (Exception ex) {
      logger.error(LogUtils.printLogStackTrace(ex));
      responseData.setStatus(Enums.ResponseStatus.ERROR.getStatus());
      responseData.setMessage(ResponseMessageConstants.ERR_SYSTEM);
    }
    return responseData;
  }

  @PostMapping("/create")
  public ResponseData createPromotion(@RequestBody PromotionRequest request){
    ResponseData responseData = new ResponseData();
    try {
      Promotion promotion = adminPromotionService.createPromotion(request);
      responseData.setData(promotion);
      responseData.setStatus(Enums.ResponseStatus.SUCCESS);
      responseData.setMessage(ResponseMessageConstants.CREATE_PROMOTION_SUCCESS);
    } catch (BusinessException be) {
      logger.error(be.getMessage());
      responseData.setMessage(be.getMessage());
      responseData.setStatus(Enums.ResponseStatus.ERROR);
    } catch (Exception ex) {
      logger.error(LogUtils.printLogStackTrace(ex));
      responseData.setMessage(ResponseMessageConstants.ERROR);
      responseData.setStatus(Enums.ResponseStatus.ERROR);
    }
    return responseData;
  }

  @PutMapping("/update")
  public ResponseData updatePromotion(@RequestBody PromotionUpdateRequest request){
    ResponseData responseData = new ResponseData();
    try {
      Promotion promotion = adminPromotionService.updatePromotion(request);
      responseData.setData(promotion);
      responseData.setStatus(Enums.ResponseStatus.SUCCESS);
      responseData.setMessage(ResponseMessageConstants.UPDATE_PROMOTION_SUCCESS);
    } catch (BusinessException be) {
      logger.error(be.getMessage());
      responseData.setMessage(be.getMessage());
      responseData.setStatus(Enums.ResponseStatus.ERROR);
    } catch (Exception ex) {
      logger.error(LogUtils.printLogStackTrace(ex));
      responseData.setMessage(ResponseMessageConstants.ERROR);
      responseData.setStatus(Enums.ResponseStatus.ERROR);
    }
    return responseData;
  }

  @DeleteMapping("/delete/{id}")
  public ResponseData deletePromotion(@PathVariable long id){
    ResponseData responseData = new ResponseData();
    try {
      adminPromotionService.deletePromotion(id);
      responseData.setStatus(Enums.ResponseStatus.SUCCESS);
      responseData.setMessage(ResponseMessageConstants.DELETE_PROMOTION_SUCCESS);
    } catch (BusinessException be) {
      logger.error(be.getMessage());
      responseData.setMessage(be.getMessage());
      responseData.setStatus(Enums.ResponseStatus.ERROR);
    } catch (Exception ex) {
      logger.error(LogUtils.printLogStackTrace(ex));
      responseData.setMessage(ResponseMessageConstants.ERROR);
      responseData.setStatus(Enums.ResponseStatus.ERROR);
    }
    return responseData;
  }
}
