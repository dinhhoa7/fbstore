package com.hoavd.fbstore.admin.service.producer.impl;


import com.hoavd.fbstore.admin.model.request.ProducerRequest;
import com.hoavd.fbstore.admin.model.request.ProducerUpdateRequest;
import com.hoavd.fbstore.admin.service.producer.AdminProducerService;
import com.hoavd.fbstore.common.constants.ResponseMessageConstants;
import com.hoavd.fbstore.common.enums.Enums;
import com.hoavd.fbstore.common.exception.BusinessException;
import com.hoavd.fbstore.common.model.Pagination;
import com.hoavd.fbstore.common.model.ResponseDataPagination;
import com.hoavd.fbstore.product.entity.Producer;
import com.hoavd.fbstore.product.service.ProducerService;
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
public class AdminProducerServiceImpl implements AdminProducerService {
  private static final Logger logger = LoggerFactory.getLogger(AdminProducerServiceImpl.class);

  @Autowired
  private ProducerService producerService;

  @Override
  public Producer createProducer(ProducerRequest request) throws Exception {
    Producer producerName = producerService.getByName(request.getName());
    if (producerName != null)
      throw new BusinessException(ResponseMessageConstants.PRODUCER_NAME_ALREADY_EXIST);
    Producer producer = new Producer();
    producer.setName(request.getName());
    producer.setDescription(request.getDescription());
    producer.create();
    return producerService.save(producer);
  }

  @Override
  public Producer updateProducer(ProducerUpdateRequest request) throws Exception {
    logger.info(request.toString());
    Producer producer = producerService.getById(request.getId());
    if (producer == null)
      throw new BusinessException(ResponseMessageConstants.PRODUCER_DOES_NOT_EXIST);
    if (Strings.isBlank(request.getName())){
      throw new BusinessException(ResponseMessageConstants.INFORMATION_INVALID);
    }
    producer.setName(request.getName());
    producer.setDescription(request.getDescription());
    producer.update();
    return producerService.save(producer);
  }

  @Override
  public void deleteProducer(long id) throws Exception {
    Producer producer = producerService.getById(id);
    if (producer == null)
      throw new BusinessException(ResponseMessageConstants.PRODUCER_DOES_NOT_EXIST);
    producerService.delete(id);
  }

  @Override
  public ResponseDataPagination getPageListProducer(int page, int size) throws Exception {
    ResponseDataPagination responseDataPagination = new ResponseDataPagination();
    int pageReq = page >= 1 ? page - 1 : page;
    Pageable pageable = PageRequest.of(pageReq, size);
    Page<Producer> producerPage = producerService.getPageList(pageable);
    List<Producer> producerList = producerPage.getContent();
    responseDataPagination.setData(producerList);
    Pagination pagination = new Pagination();
    pagination.setCurrentPage(page);
    pagination.setPageSize(size);
    pagination.setTotalPage(producerPage.getTotalPages());
    pagination.setTotalRecords(producerPage.getTotalElements());
    responseDataPagination.setStatus(Enums.ResponseStatus.SUCCESS.getStatus());
    responseDataPagination.setPagination(pagination);
    return responseDataPagination;
  }
}
