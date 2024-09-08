package com.hoavd.fbstore.admin.service.user.impl;

import com.hoavd.fbstore.admin.config.security.JwtTokenProvider;
import com.hoavd.fbstore.admin.model.request.*;
import com.hoavd.fbstore.admin.service.user.AdminUserService;
import com.hoavd.fbstore.common.constants.ResponseMessageConstants;
import com.hoavd.fbstore.common.enums.Enums;
import com.hoavd.fbstore.common.exception.BusinessException;
import com.hoavd.fbstore.common.model.Pagination;
import com.hoavd.fbstore.common.model.ResponseDataPagination;
import com.hoavd.fbstore.user.entity.Admin;
import com.hoavd.fbstore.user.entity.User;
import com.hoavd.fbstore.user.service.AdminService;
import com.hoavd.fbstore.user.service.UserService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminUserServiceImpl implements AdminUserService {
  @Autowired
  private AdminService adminService;

  @Autowired
  private UserService userService;

  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  @Autowired
  private PasswordEncoder passwordEncoder;
  @Override
  public String login(LoginRequest request) throws Exception {
    Admin admin = adminService.getByUsername(request.getUsername().toLowerCase());
    if (admin == null)
      throw new BusinessException(ResponseMessageConstants.ADMIN_DOES_NOT_EXIST);
    if (passwordEncoder.matches(request.getPassword(), admin.getPassword()))
      return jwtTokenProvider.generateToken(admin);
    throw new BusinessException(ResponseMessageConstants.ERROR);
  }

  @Override
  public Admin createAdmin(AdminRequest request) throws Exception {
    Admin adminUsername = adminService.getByUsername(request.getUsername().toLowerCase());
    if (adminUsername != null)
      throw new BusinessException(ResponseMessageConstants.USERNAME_ALREADY_EXIST);
    Admin admin = new Admin();
    admin.setUsername(request.getUsername().toLowerCase());
    admin.setPassword(passwordEncoder.encode(request.getPassword()));
    admin.setStatus(true);
    admin.setDeleted(false);
    admin.create();
    return adminService.save(admin);
  }

  @Override
  public User createUser(UserRequest request) throws Exception {
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
  public Admin updateAdmin(AdminUpdateRequest request) throws Exception {
    Admin admin = adminService.getAdminById(request.getId());
    if (admin == null)
      throw new BusinessException(ResponseMessageConstants.ADMIN_DOES_NOT_EXIST);
    if (Strings.isBlank(request.getUsername()) || Strings.isBlank(request.getPassword())){
      throw new BusinessException(ResponseMessageConstants.INFORMATION_INVALID);
    }
    admin.setUsername(request.getUsername().toLowerCase());
    admin.setPassword(passwordEncoder.encode(request.getPassword()));
    admin.setStatus(request.isStatus());
    admin.update();
    return adminService.save(admin);
  }

  @Override
  public User updateUser(UserUpdateRequest request) throws Exception {
    User user = userService.getUserById(request.getId());
    if (user == null)
      throw new BusinessException(ResponseMessageConstants.USER_DOES_NOT_EXIST);
    if (Strings.isBlank(request.getEmail()) || Strings.isBlank(request.getPassword()) || Strings.isBlank(request.getFullName()) ||
      Strings.isBlank(request.getAddress()) || Strings.isBlank(request.getPhoneNumber())) {
      throw new BusinessException(ResponseMessageConstants.INFORMATION_INVALID);
    }
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

  @Override
  public Admin deleteAdmin(long id) throws Exception {
    Admin admin = adminService.getAdminById(id);
    if (admin == null)
      throw new BusinessException(ResponseMessageConstants.ADMIN_DOES_NOT_EXIST);
    admin.setDeleted(true);
    return adminService.save(admin);
  }

  @Override
  public User deleteUser(long id) throws Exception {
    User user = userService.getUserById(id);
    if (user == null)
      throw new BusinessException(ResponseMessageConstants.USER_DOES_NOT_EXIST);
    user.setDeleted(true);
    return userService.save(user);
  }

  @Override
  public ResponseDataPagination getPageListAdmin(int page, int size) throws Exception {
    ResponseDataPagination responseDataPagination = new ResponseDataPagination();
    int pageReq = page >= 1 ? page - 1 : page;
    Pageable pageable = PageRequest.of(pageReq, size);
    Page<Admin> adminPage = adminService.getPageListAdmin(pageable);
    List<Admin> adminList = adminPage.getContent();
    responseDataPagination.setData(adminList);
    Pagination pagination = new Pagination();
    pagination.setCurrentPage(page);
    pagination.setPageSize(size);
    pagination.setTotalPage(adminPage.getTotalPages());
    pagination.setTotalRecords(adminPage.getTotalElements());
    responseDataPagination.setStatus(Enums.ResponseStatus.SUCCESS.getStatus());
    responseDataPagination.setPagination(pagination);
    return responseDataPagination;
  }

  @Override
  public ResponseDataPagination getPageListUser(int page, int size) throws Exception {
    ResponseDataPagination responseDataPagination = new ResponseDataPagination();
    int pageReq = page >= 1 ? page - 1 : page;
    Pageable pageable = PageRequest.of(pageReq, size);
    Page<User> userPage = userService.getPageListUser(pageable);
    List<User> userList = userPage.getContent();
    responseDataPagination.setData(userList);
    Pagination pagination = new Pagination();
    pagination.setCurrentPage(page);
    pagination.setPageSize(size);
    pagination.setTotalPage(userPage.getTotalPages());
    pagination.setTotalRecords(userPage.getTotalElements());
    responseDataPagination.setStatus(Enums.ResponseStatus.SUCCESS.getStatus());
    responseDataPagination.setPagination(pagination);
    return responseDataPagination;
  }
}
