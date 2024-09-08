package com.hoavd.fbstore.customer.controller;

import com.hoavd.fbstore.common.constants.ResponseMessageConstants;
import com.hoavd.fbstore.common.enums.Enums;
import com.hoavd.fbstore.common.exception.BusinessException;
import com.hoavd.fbstore.common.model.ResponseData;
import com.hoavd.fbstore.common.utils.LogUtils;
import com.hoavd.fbstore.customer.model.request.UserRequest;
import com.hoavd.fbstore.customer.model.request.UserUpdateRequest;
import com.hoavd.fbstore.customer.service.user.CustomerUserService;
import com.hoavd.fbstore.user.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
  private static final Logger logger = LoggerFactory.getLogger(UserController.class);

  @Autowired
  private CustomerUserService customerUserService;

  @PostMapping("/create")
  public ResponseData createUser(@RequestBody UserRequest request){
    ResponseData responseData = new ResponseData();
    try {
      User user = customerUserService.create(request);
      responseData.setData(user);
      responseData.setStatus(Enums.ResponseStatus.SUCCESS);
      responseData.setMessage(ResponseMessageConstants.CREATE_ACC_USER_SUCCESS);
    } catch (BusinessException be) {
      logger.error(be.getMessage());
      responseData.setMessage(be.getMessage());
      responseData.setStatus(Enums.ResponseStatus.ERROR);
    } catch (Exception ex) {
      logger.error(LogUtils.printLogStackTrace(ex));
      responseData.setMessage(ResponseMessageConstants.ERROR);
      responseData.setStatus(Enums.ResponseStatus.ERROR);
    }
    return responseData;
  }

  @PutMapping("/update")
  public ResponseData updateUser(@RequestBody UserUpdateRequest request){
    ResponseData responseData = new ResponseData();
    try {
      User user = customerUserService.update(request);
      responseData.setData(user);
      responseData.setStatus(Enums.ResponseStatus.SUCCESS);
      responseData.setMessage(ResponseMessageConstants.UPDATE_ACC_USER_SUCCESS);
    } catch (BusinessException be) {
      logger.error(be.getMessage());
      responseData.setMessage(be.getMessage());
      responseData.setStatus(Enums.ResponseStatus.ERROR);
    } catch (Exception ex) {
      logger.error(LogUtils.printLogStackTrace(ex));
      responseData.setMessage(ResponseMessageConstants.ERROR);
      responseData.setStatus(Enums.ResponseStatus.ERROR);
    }
    return responseData;
  }
}
