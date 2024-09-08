package com.hoavd.fbstore.admin.service.producer;

import com.hoavd.fbstore.admin.model.request.ProducerRequest;
import com.hoavd.fbstore.admin.model.request.ProducerUpdateRequest;
import com.hoavd.fbstore.common.model.ResponseDataPagination;
import com.hoavd.fbstore.product.entity.Producer;

public interface AdminProducerService {
  Producer createProducer(ProducerRequest request) throws Exception;

  Producer updateProducer(ProducerUpdateRequest request) throws Exception;

  void deleteProducer(long id) throws Exception;

  ResponseDataPagination getPageListProducer(int page, int size) throws Exception;
}
