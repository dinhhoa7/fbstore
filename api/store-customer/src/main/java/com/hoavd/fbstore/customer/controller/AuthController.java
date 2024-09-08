package com.hoavd.fbstore.customer.controller;

import com.hoavd.fbstore.common.constants.ResponseMessageConstants;
import com.hoavd.fbstore.common.enums.Enums;
import com.hoavd.fbstore.common.exception.BusinessException;
import com.hoavd.fbstore.common.model.ResponseData;
import com.hoavd.fbstore.common.utils.LogUtils;
import com.hoavd.fbstore.customer.model.request.LoginRequest;
import com.hoavd.fbstore.customer.service.user.CustomerUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
  private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

  @Autowired
  private CustomerUserService customerUserService;

  @PostMapping("/login")
  public ResponseData login(@RequestBody LoginRequest request){
    ResponseData responseData = new ResponseData();
    try {
      String jwt = customerUserService.login(request);
      Map<String, Object> authResponse = new HashMap<>();
      authResponse.put("access_token", jwt);
      responseData.setData(authResponse);
      responseData.setStatus(Enums.ResponseStatus.SUCCESS);
      responseData.setMessage(ResponseMessageConstants.LOGIN_SUCCESS);
    } catch (BusinessException be) {
      LOGGER.error(be.getMessage());
      responseData.setMessage(be.getMessage());
      responseData.setStatus(Enums.ResponseStatus.ERROR);
    } catch (Exception ex) {
      LOGGER.error(LogUtils.printLogStackTrace(ex));
      responseData.setMessage(ResponseMessageConstants.ERROR);
      responseData.setStatus(Enums.ResponseStatus.ERROR);
    }
    return responseData;
  }
}

