package com.hoavd.fbstore.product.service;

import com.hoavd.fbstore.product.entity.Producer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProducerService {
  Page<Producer> getPageList(Pageable pageable);

  Producer getById(long id);

  Producer getByName(String name);

  Producer save(Producer producer);

  void delete(long id);
}
