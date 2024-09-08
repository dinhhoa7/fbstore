package com.hoavd.fbstore.admin.controller;

import com.hoavd.fbstore.admin.model.request.CategoryRequest;
import com.hoavd.fbstore.admin.model.request.CategoryUpdateRequest;
import com.hoavd.fbstore.admin.model.request.ProducerRequest;
import com.hoavd.fbstore.admin.model.request.ProducerUpdateRequest;
import com.hoavd.fbstore.admin.service.category.AdminCategoryService;
import com.hoavd.fbstore.admin.service.producer.AdminProducerService;
import com.hoavd.fbstore.common.constants.PagingConstants;
import com.hoavd.fbstore.common.constants.ResponseMessageConstants;
import com.hoavd.fbstore.common.enums.Enums;
import com.hoavd.fbstore.common.exception.BusinessException;
import com.hoavd.fbstore.common.model.ResponseData;
import com.hoavd.fbstore.common.utils.LogUtils;
import com.hoavd.fbstore.product.entity.Category;
import com.hoavd.fbstore.product.entity.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/producer")
public class ProducerController {
  private static final Logger logger = LoggerFactory.getLogger(ProducerController.class);

  @Autowired
  private AdminProducerService adminProducerService;

  @GetMapping("/get-list")
  public ResponseData getListProducer(@RequestParam(value = "page", required = false, defaultValue = PagingConstants.PAGING_STRING_PAGE_DEFAULT) int page,
    @RequestParam(value = "size", required = false, defaultValue = PagingConstants.PAGING_STRING_SIZE_DEFAULT) int size) {
    ResponseData responseData = new ResponseData();
    try {
      return adminProducerService.getPageListProducer(page, size);
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
  public ResponseData createProducer(@RequestBody ProducerRequest request){
    ResponseData responseData = new ResponseData();
    try {
      Producer producer = adminProducerService.createProducer(request);
      responseData.setData(producer);
      responseData.setStatus(Enums.ResponseStatus.SUCCESS);
      responseData.setMessage(ResponseMessageConstants.CREATE_PRODUCER_SUCCESS);
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
  public ResponseData updateProducer(@RequestBody ProducerUpdateRequest request){
    ResponseData responseData = new ResponseData();
    try {
      Producer producer = adminProducerService.updateProducer(request);
      responseData.setData(producer);
      responseData.setStatus(Enums.ResponseStatus.SUCCESS);
      responseData.setMessage(ResponseMessageConstants.UPDATE_PRODUCER_SUCCESS);
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
  public ResponseData deleteProducer(@PathVariable long id){
    ResponseData responseData = new ResponseData();
    try {
      adminProducerService.deleteProducer(id);
      responseData.setStatus(Enums.ResponseStatus.SUCCESS);
      responseData.setMessage(ResponseMessageConstants.DELETE_PRODUCER_SUCCESS);
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
