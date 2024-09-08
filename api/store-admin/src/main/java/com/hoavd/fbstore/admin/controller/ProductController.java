package com.hoavd.fbstore.admin.controller;

import com.hoavd.fbstore.admin.model.request.ProductRequest;
import com.hoavd.fbstore.admin.model.request.ProductUpdateRequest;
import com.hoavd.fbstore.admin.model.request.PromotionProductRequest;
import com.hoavd.fbstore.admin.service.product.AdminProductService;
import com.hoavd.fbstore.common.constants.PagingConstants;
import com.hoavd.fbstore.common.constants.ResponseMessageConstants;
import com.hoavd.fbstore.common.enums.Enums;
import com.hoavd.fbstore.common.exception.BusinessException;
import com.hoavd.fbstore.common.model.ResponseData;
import com.hoavd.fbstore.common.utils.LogUtils;
import com.hoavd.fbstore.product.entity.Product;
import com.hoavd.fbstore.product.entity.PromotionProduct;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController {
  private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

  @Autowired
  private AdminProductService adminProductService;

  @GetMapping("/get-list")
  public ResponseData getListProduct(
    @RequestParam(value = "name", required = false, defaultValue = StringUtils.EMPTY) String productName,
    @RequestParam(value = "category", required = false, defaultValue = "0") long category,
    @RequestParam(value = "page", required = false, defaultValue = PagingConstants.PAGING_STRING_PAGE_DEFAULT) int page,
    @RequestParam(value = "size", required = false, defaultValue = PagingConstants.PAGING_STRING_SIZE_DEFAULT) int size) {
    ResponseData responseData = new ResponseData();
    try {
      return adminProductService.getPageListProduct(productName, category, page, size);
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
  public ResponseData createProduct(@RequestBody ProductRequest request){
    ResponseData responseData = new ResponseData();
    try {
      Product product = adminProductService.createProduct(request);
      responseData.setData(product);
      responseData.setStatus(Enums.ResponseStatus.SUCCESS);
      responseData.setMessage(ResponseMessageConstants.CREATE_PRODUCT_SUCCESS);
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
  public ResponseData updateProduct(@RequestBody ProductUpdateRequest request){
    ResponseData responseData = new ResponseData();
    try {
      Product product = adminProductService.updateProduct(request);
      responseData.setData(product);
      responseData.setStatus(Enums.ResponseStatus.SUCCESS);
      responseData.setMessage(ResponseMessageConstants.UPDATE_PRODUCT_SUCCESS);
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
  public ResponseData deleteProduct(@PathVariable long id){
    ResponseData responseData = new ResponseData();
    try {
      adminProductService.deleteProduct(id);
      responseData.setStatus(Enums.ResponseStatus.SUCCESS);
      responseData.setMessage(ResponseMessageConstants.DELETE_PRODUCT_SUCCESS);
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

  @PostMapping("/add-promotion")
  public ResponseData addPromotion(@RequestBody PromotionProductRequest request){
    ResponseData responseData = new ResponseData();
    try {
      PromotionProduct promotionProduct = adminProductService.addPromotionProduct(request);
      responseData.setData(promotionProduct);
      responseData.setStatus(Enums.ResponseStatus.SUCCESS);
      responseData.setMessage(ResponseMessageConstants.CREATE_PROMOTION_PRODUCT_SUCCESS);
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

  @DeleteMapping("/remove-promotion/{id}")
  public ResponseData removePromotion(@PathVariable long id){
    ResponseData responseData = new ResponseData();
    try {
      adminProductService.removePromotionProduct(id);
      responseData.setStatus(Enums.ResponseStatus.SUCCESS);
      responseData.setMessage(ResponseMessageConstants.REMOVE_PROMOTION_PRODUCT_SUCCESS);
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
