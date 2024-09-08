package com.hoavd.fbstore.product.service.impl;

import com.hoavd.fbstore.product.entity.Product;
import com.hoavd.fbstore.product.repository.ProductRepository;
import com.hoavd.fbstore.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
  @Autowired
  private ProductRepository productRepository;

  @Override
  public Page<Product> getPageListProduct(String name, long categoryId, Pageable pageable) {
    return productRepository.getPageListProduct(name, categoryId, pageable);
  }

  @Override
  public List<Product> getList(String name) {
    return productRepository.getList(name);
  }

  @Override
  public Product getProductById(long id) {
    return productRepository.findById(id);
  }

  @Override
  public Product getByName(String name) {
    return productRepository.findByName(name);
  }

  @Override
  public Product save(Product product) {
    return productRepository.save(product);
  }

  @Override
  public void delete(long id) {
    productRepository.deleteById(id);
  }
}
