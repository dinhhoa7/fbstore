package com.hoavd.fbstore.admin.service.product.impl;

import com.hoavd.fbstore.admin.model.request.ProductRequest;
import com.hoavd.fbstore.admin.model.request.ProductUpdateRequest;
import com.hoavd.fbstore.admin.model.request.PromotionProductRequest;
import com.hoavd.fbstore.admin.model.response.ProductResponse;
import com.hoavd.fbstore.admin.service.product.AdminProductService;
import com.hoavd.fbstore.common.constants.ResponseMessageConstants;
import com.hoavd.fbstore.common.enums.Enums;
import com.hoavd.fbstore.common.exception.BusinessException;
import com.hoavd.fbstore.common.model.Pagination;
import com.hoavd.fbstore.common.model.ResponseDataPagination;
import com.hoavd.fbstore.product.entity.*;
import com.hoavd.fbstore.product.service.*;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminProductServiceImpl implements AdminProductService {
  private static final Logger logger = LoggerFactory.getLogger(AdminProductServiceImpl.class);

  @Autowired
  private ProductService productService;

  @Autowired
  private CategoryService categoryService;

  @Autowired
  private ProducerService producerService;

  @Autowired
  private PromotionService promotionService;

  @Autowired
  private PromotionProductService promotionProductService;

  @Override
  public Product createProduct(ProductRequest request) throws Exception {
    Product productName = productService.getByName(request.getName());
    if (productName != null)
      throw new BusinessException(ResponseMessageConstants.PRODUCT_NAME_ALREADY_EXIST);
    Category categoryObj = categoryService.getById(request.getCategoryId());
    if (categoryObj == null)
      throw new BusinessException(ResponseMessageConstants.CATEGORY_DOES_NOT_EXIST);
    Producer producerObj = producerService.getById(request.getProducerId());
    if (producerObj == null)
      throw new BusinessException(ResponseMessageConstants.PRODUCER_DOES_NOT_EXIST);
    Product product = new Product();
    product.setCode(request.getCode());
    product.setCategoryId(request.getCategoryId());
    product.setName(request.getName());
    product.setDescription(request.getDescription());
    product.setProducerId(request.getProducerId());
    product.setStatus(true);
    product.create();
    return productService.save(product);
  }

  @Override
  public Product updateProduct(ProductUpdateRequest request) throws Exception {
    logger.info(request.toString());
    Product product = productService.getProductById(request.getId());
    if (product == null)
      throw new BusinessException(ResponseMessageConstants.PRODUCT_DOES_NOT_EXIST);
    if (Strings.isBlank(request.getName()))
      throw new BusinessException(ResponseMessageConstants.INFORMATION_INVALID);
    Category categoryObj = categoryService.getById(request.getCategoryId());
    if (categoryObj == null)
      throw new BusinessException(ResponseMessageConstants.CATEGORY_DOES_NOT_EXIST);
    Producer producerObj = producerService.getById(request.getProducerId());
    if (producerObj == null)
      throw new BusinessException(ResponseMessageConstants.PRODUCER_DOES_NOT_EXIST);
    product.setCode(request.getCode());
    product.setCategoryId(request.getCategoryId());
    product.setName(request.getName());
    product.setDescription(request.getDescription());
    product.setProducerId(request.getProducerId());
    product.setStatus(request.isStatus());
    product.update();
    return productService.save(product);
  }

  @Override
  public void deleteProduct(long id) throws Exception {
    Product product = productService.getProductById(id);
    if (product == null)
      throw new BusinessException(ResponseMessageConstants.PRODUCT_DOES_NOT_EXIST);
    productService.delete(id);
  }

  @Override
  public ResponseDataPagination getPageListProduct(String name, long category, int page, int size) throws Exception {
    int pageReq = page >= 1 ? page - 1 : page;
    Pageable pageable = PageRequest.of(pageReq, size);
    Page<Product> productPage = productService.getPageListProduct(name, category, pageable);
    List<Product> productList = productPage.getContent();
    List<ProductResponse> productDetailResponseList = new ArrayList<>();
    for (Product pr : productList) {
      ProductResponse res = new ProductResponse();
      Category categoryObj = categoryService.getById(pr.getCategoryId());
      Producer producerObj = producerService.getById(pr.getProducerId());
      res.setId(pr.getId());
      res.setCode(pr.getCode());
      res.setCategoryId(pr.getCategoryId());
      res.setCategory(categoryObj.getName());
      res.setName(pr.getName());
      res.setDescription(pr.getDescription());
      res.setProducerId(pr.getProducerId());
      res.setProducer(producerObj.getName());
      res.setStatus(pr.isStatus());
      productDetailResponseList.add(res);
    }
    ResponseDataPagination responseDataPagination = new ResponseDataPagination();
    Pagination pagination = new Pagination();
    responseDataPagination.setData(productDetailResponseList);
    pagination.setCurrentPage(page);
    pagination.setPageSize(size);
    pagination.setTotalPage(productPage.getTotalPages());
    pagination.setTotalRecords(productPage.getTotalElements());
    responseDataPagination.setStatus(Enums.ResponseStatus.SUCCESS.getStatus());
    responseDataPagination.setPagination(pagination);
    return responseDataPagination;
  }

  @Override
  public PromotionProduct addPromotionProduct(PromotionProductRequest request) throws Exception {
    Product product = productService.getProductById(request.getProductId());
    if (product == null)
      throw new BusinessException(ResponseMessageConstants.PRODUCT_DOES_NOT_EXIST);
    Promotion promotion = promotionService.getById(request.getPromotionId());
    if (promotion == null)
      throw new BusinessException(ResponseMessageConstants.PROMOTION_DOES_NOT_EXIST);
    PromotionProduct promotionProduct = new PromotionProduct();
    promotionProduct.setProductId(request.getProductId());
    promotionProduct.setPromotionId(request.getPromotionId());
    promotionProduct.setStatus(true);
    promotionProduct.create();
    return promotionProductService.save(promotionProduct);
  }

  @Override
  public void removePromotionProduct(long id) throws Exception {
    PromotionProduct promotionProduct = promotionProductService.getById(id);
    if (promotionProduct == null)
      throw new BusinessException(ResponseMessageConstants.PROMOTION_PRODUCT_DOES_NOT_EXIST);
    promotionProductService.delete(id);
  }
}
