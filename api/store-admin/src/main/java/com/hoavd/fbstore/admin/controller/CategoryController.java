package com.hoavd.fbstore.admin.controller;

import com.hoavd.fbstore.admin.model.request.CategoryRequest;
import com.hoavd.fbstore.admin.model.request.CategoryUpdateRequest;
import com.hoavd.fbstore.admin.service.category.AdminCategoryService;
import com.hoavd.fbstore.common.constants.PagingConstants;
import com.hoavd.fbstore.common.constants.ResponseMessageConstants;
import com.hoavd.fbstore.common.enums.Enums;
import com.hoavd.fbstore.common.exception.BusinessException;
import com.hoavd.fbstore.common.model.ResponseData;
import com.hoavd.fbstore.common.utils.LogUtils;
import com.hoavd.fbstore.product.entity.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
  private static final Logger logger = LoggerFactory.getLogger(CategoryController.class);

  @Autowired
  private AdminCategoryService adminCategoryService;

  @GetMapping("/get-list")
  public ResponseData getListCategory(@RequestParam(value = "page", required = false, defaultValue = PagingConstants.PAGING_STRING_PAGE_DEFAULT) int page,
    @RequestParam(value = "size", required = false, defaultValue = PagingConstants.PAGING_STRING_SIZE_DEFAULT) int size) {
    ResponseData responseData = new ResponseData();
    try {
      return adminCategoryService.getPageListCategory(page, size);
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
  public ResponseData createCategory(@RequestBody CategoryRequest request){
    ResponseData responseData = new ResponseData();
    try {
      Category category = adminCategoryService.createCategory(request);
      responseData.setData(category);
      responseData.setStatus(Enums.ResponseStatus.SUCCESS);
      responseData.setMessage(ResponseMessageConstants.CREATE_CATEGORY_SUCCESS);
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
  public ResponseData updateCategory(@RequestBody CategoryUpdateRequest request){
    ResponseData responseData = new ResponseData();
    try {
      Category category = adminCategoryService.updateCategory(request);
      responseData.setData(category);
      responseData.setStatus(Enums.ResponseStatus.SUCCESS);
      responseData.setMessage(ResponseMessageConstants.UPDATE_CATEGORY_SUCCESS);
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
  public ResponseData deleteCategory(@PathVariable long id){
    ResponseData responseData = new ResponseData();
    try {
      adminCategoryService.deleteCategory(id);
      responseData.setStatus(Enums.ResponseStatus.SUCCESS);
      responseData.setMessage(ResponseMessageConstants.DELETE_CATEGORY_SUCCESS);
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
