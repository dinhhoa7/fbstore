package com.hoavd.fbstore.product.service;


import com.hoavd.fbstore.product.entity.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
  Page<Category> getPageList(Pageable pageable);

  Category getById(long id);

  Category getByName(String name);

  Category save(Category category);

  void delete(long id);
}
