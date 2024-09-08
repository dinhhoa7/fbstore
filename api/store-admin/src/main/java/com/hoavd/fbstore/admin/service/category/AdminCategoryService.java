package com.hoavd.fbstore.admin.service.category;

import com.hoavd.fbstore.admin.model.request.CategoryRequest;
import com.hoavd.fbstore.admin.model.request.CategoryUpdateRequest;
import com.hoavd.fbstore.common.model.ResponseDataPagination;
import com.hoavd.fbstore.product.entity.Category;

public interface AdminCategoryService {
  Category createCategory(CategoryRequest request) throws Exception;

  Category updateCategory(CategoryUpdateRequest request) throws Exception;

  void deleteCategory(long id) throws Exception;

  ResponseDataPagination getPageListCategory(int page, int size) throws Exception;
}
