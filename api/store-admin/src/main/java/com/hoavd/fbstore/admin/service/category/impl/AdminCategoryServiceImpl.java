package com.hoavd.fbstore.admin.service.category.impl;


import com.hoavd.fbstore.admin.model.request.CategoryRequest;
import com.hoavd.fbstore.admin.model.request.CategoryUpdateRequest;
import com.hoavd.fbstore.admin.service.category.AdminCategoryService;
import com.hoavd.fbstore.common.constants.ResponseMessageConstants;
import com.hoavd.fbstore.common.enums.Enums;
import com.hoavd.fbstore.common.exception.BusinessException;
import com.hoavd.fbstore.common.model.Pagination;
import com.hoavd.fbstore.common.model.ResponseDataPagination;
import com.hoavd.fbstore.product.entity.Category;
import com.hoavd.fbstore.product.service.CategoryService;
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
public class AdminCategoryServiceImpl implements AdminCategoryService {
  private static final Logger logger = LoggerFactory.getLogger(AdminCategoryServiceImpl.class);

  @Autowired
  private CategoryService categoryService;

  @Override
  public Category createCategory(CategoryRequest request) throws Exception {
    Category categoryName = categoryService.getByName(request.getName());
    if (categoryName != null)
      throw new BusinessException(ResponseMessageConstants.CATEGORY_NAME_ALREADY_EXIST);
    Category category = new Category();
    category.setName(request.getName());
    category.setDescription(request.getDescription());
    category.setStatus(true);
    category.create();
    return categoryService.save(category);
  }

  @Override
  public Category updateCategory(CategoryUpdateRequest request) throws Exception {
    logger.info(request.toString());
    Category category = categoryService.getById(request.getId());
    if (category == null)
      throw new BusinessException(ResponseMessageConstants.CATEGORY_DOES_NOT_EXIST);
    if (Strings.isBlank(request.getName())){
      throw new BusinessException(ResponseMessageConstants.INFORMATION_INVALID);
    }
    category.setName(request.getName());
    category.setDescription(request.getDescription());
    category.setStatus(request.isStatus());
    category.update();
    return categoryService.save(category);
  }

  @Override
  public void deleteCategory(long id) throws Exception {
    Category category = categoryService.getById(id);
    if (category == null)
      throw new BusinessException(ResponseMessageConstants.CATEGORY_DOES_NOT_EXIST);
    categoryService.delete(id);
  }

  @Override
  public ResponseDataPagination getPageListCategory(int page, int size) throws Exception {
    ResponseDataPagination responseDataPagination = new ResponseDataPagination();
    int pageReq = page >= 1 ? page - 1 : page;
    Pageable pageable = PageRequest.of(pageReq, size);
    Page<Category> categoryPage = categoryService.getPageList(pageable);
    List<Category> categoryList = categoryPage.getContent();
    responseDataPagination.setData(categoryList);
    Pagination pagination = new Pagination();
    pagination.setCurrentPage(page);
    pagination.setPageSize(size);
    pagination.setTotalPage(categoryPage.getTotalPages());
    pagination.setTotalRecords(categoryPage.getTotalElements());
    responseDataPagination.setStatus(Enums.ResponseStatus.SUCCESS.getStatus());
    responseDataPagination.setPagination(pagination);
    return responseDataPagination;
  }
}
