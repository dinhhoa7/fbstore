package com.hoavd.fbstore.customer.service.user.impl;

import com.hoavd.fbstore.common.constants.ResponseMessageConstants;
import com.hoavd.fbstore.common.exception.BusinessException;
import com.hoavd.fbstore.customer.config.security.JwtTokenProvider;
import com.hoavd.fbstore.customer.model.request.LoginRequest;
import com.hoavd.fbstore.customer.model.request.UserRequest;
import com.hoavd.fbstore.customer.model.request.UserUpdateRequest;
import com.hoavd.fbstore.customer.service.user.CustomerUserService;
import com.hoavd.fbstore.user.entity.User;
import com.hoavd.fbstore.user.service.UserService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomerUserServiceImpl implements CustomerUserService {
  @Autowired
  private UserService userService;

  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public String login(LoginRequest request) throws Exception {
    User user = userService.getByUsername(request.getUsername().toLowerCase());
    if (user == null)
      throw new BusinessException(ResponseMessageConstants.ADMIN_DOES_NOT_EXIST);
    if (passwordEncoder.matches(request.getPassword(), user.getPassword()))
      return jwtTokenProvider.generateToken(user);
    throw new BusinessException(ResponseMessageConstants.ERROR);
  }

  @Override
  public User create(UserRequest request) throws Exception {
    User username = userService.getByUsername(request.getEmail().toLowerCase());
    if (username != null)
      throw new BusinessException(ResponseMessageConstants.USERNAME_ALREADY_EXIST);
    User user = new User();
    user.setUsername(request.getUsername().toLowerCase());
    user.setPassword(passwordEncoder.encode(request.getPassword()));
    user.setFullName(request.getFullName());
    user.setDob(request.getDob());
    user.setGender(request.getGender());
    user.setEmail(request.getEmail().toLowerCase());
    user.setAddress(request.getAddress());
    user.setPhoneNumber(request.getPhoneNumber());
    user.create();
    return userService.save(user);
  }

  @Override
  public User update(UserUpdateRequest request) throws Exception {
    User user = userService.getUserById(request.getId());
    if (user == null)
      throw new BusinessException(ResponseMessageConstants.USER_DOES_NOT_EXIST);
    if (Strings.isBlank(request.getEmail()) || Strings.isBlank(request.getPassword()) || Strings.isBlank(request.getFullName()) ||
      Strings.isBlank(request.getAddress()) || Strings.isBlank(request.getPhoneNumber()))
      throw new BusinessException(ResponseMessageConstants.INFORMATION_INVALID);
    user.setUsername(request.getUsername().toLowerCase());
    user.setPassword(passwordEncoder.encode(request.getPassword()));
    user.setFullName(request.getFullName());
    user.setDob(request.getDob());
    user.setGender(request.getGender());
    user.setEmail(request.getEmail().toLowerCase());
    user.setAddress(request.getAddress());
    user.setPhoneNumber(request.getPhoneNumber());
    user.setStatus(request.isStatus());
    user.update();
    return userService.save(user);
  }
}
