package com.hoavd.fbstore.product.service.impl;


import com.hoavd.fbstore.product.entity.Category;
import com.hoavd.fbstore.product.repository.CategoryRepository;
import com.hoavd.fbstore.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
  @Autowired
  private CategoryRepository categoryRepository;

  @Override
  public Page<Category> getPageList(Pageable pageable) {
    return categoryRepository.getPageListCategory(pageable);
  }

  @Override
  public Category getById(long id) {
    return categoryRepository.findById(id);
  }

  @Override
  public Category getByName(String name) {
    return categoryRepository.findByName(name);
  }

  @Override
  public Category save(Category category) {
    return categoryRepository.save(category);
  }

  @Override
  public void delete(long id) {
    categoryRepository.deleteById(id);
  }
}
