package com.hoavd.fbstore.admin.service.product;

import com.hoavd.fbstore.admin.model.request.ProductRequest;
import com.hoavd.fbstore.admin.model.request.ProductUpdateRequest;
import com.hoavd.fbstore.admin.model.request.PromotionProductRequest;
import com.hoavd.fbstore.common.model.ResponseDataPagination;
import com.hoavd.fbstore.product.entity.Product;
import com.hoavd.fbstore.product.entity.PromotionProduct;

public interface AdminProductService {
  Product createProduct(ProductRequest request) throws Exception;

  Product updateProduct(ProductUpdateRequest request) throws Exception;

  void deleteProduct(long id) throws Exception;

  ResponseDataPagination getPageListProduct(String name, long categoryId, int page, int size) throws Exception;

  PromotionProduct addPromotionProduct(PromotionProductRequest request) throws Exception;

  void removePromotionProduct(long id) throws Exception;
}
