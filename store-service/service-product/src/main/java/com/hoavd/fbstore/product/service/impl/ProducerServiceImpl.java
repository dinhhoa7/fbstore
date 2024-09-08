package com.hoavd.fbstore.product.service.impl;

import com.hoavd.fbstore.product.entity.Producer;
import com.hoavd.fbstore.product.repository.ProducerRepository;
import com.hoavd.fbstore.product.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProducerServiceImpl implements ProducerService {
  @Autowired
  private ProducerRepository producerRepository;

  @Override
  public Page<Producer> getPageList(Pageable pageable) {
    return producerRepository.getPageList(pageable);
  }

  @Override
  public Producer getById(long id) {
    return producerRepository.findById(id);
  }

  @Override
  public Producer getByName(String name) {
    return producerRepository.findByName(name);
  }

  @Override
  public Producer save(Producer producer) {
    return producerRepository.save(producer);
  }

  @Override
  public void delete(long id) {
    producerRepository.deleteById(id);
  }
}
