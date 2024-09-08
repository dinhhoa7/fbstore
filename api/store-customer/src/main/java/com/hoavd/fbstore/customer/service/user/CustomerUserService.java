package com.hoavd.fbstore.customer.service.user;

import com.hoavd.fbstore.customer.model.request.LoginRequest;
import com.hoavd.fbstore.customer.model.request.UserRequest;
import com.hoavd.fbstore.customer.model.request.UserUpdateRequest;
import com.hoavd.fbstore.user.entity.User;

public interface CustomerUserService {
  String login(LoginRequest request) throws Exception;

  User create(UserRequest request) throws Exception;

  User update(UserUpdateRequest request) throws Exception;
}
