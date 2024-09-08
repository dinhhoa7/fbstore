package com.hoavd.fbstore.admin.service.user;

import com.hoavd.fbstore.admin.model.request.*;
import com.hoavd.fbstore.common.model.ResponseDataPagination;
import com.hoavd.fbstore.user.entity.Admin;
import com.hoavd.fbstore.user.entity.User;

public interface AdminUserService {
  String login(LoginRequest request) throws Exception;

  Admin createAdmin(AdminRequest request) throws Exception;

  User createUser(UserRequest request) throws Exception;

  Admin updateAdmin(AdminUpdateRequest request) throws Exception;

  User updateUser(UserUpdateRequest request) throws Exception;

  Admin deleteAdmin(long id) throws Exception;

  User  deleteUser(long id) throws Exception;

  ResponseDataPagination getPageListAdmin(int page, int size) throws Exception;

  ResponseDataPagination getPageListUser(int page, int size) throws Exception;
}
