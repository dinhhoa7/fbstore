package com.hoavd.fbstore.user.service.impl;

import com.hoavd.fbstore.user.entity.Admin;
import com.hoavd.fbstore.user.repository.AdminRepository;
import com.hoavd.fbstore.user.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
  @Autowired
  private AdminRepository adminRepository;

  @Override
  public Page<Admin> getPageListAdmin(Pageable pageable) {
    return adminRepository.getPageList(pageable);
  }

  @Override
  public Admin getAdminById(long id) {
    return adminRepository.findById(id);
  }

  public Admin getByUsername(String username) {
    return adminRepository.findByUsername(username);
  }

  public Admin getByIdAndUsername(long id, String username) {
    return adminRepository.findByIdAndUsername(id, username);
  }

  @Override
  public Admin save(Admin admin) {
    return adminRepository.save(admin);
  }

  @Override
  public void delete(long id) {
    adminRepository.deleteById(id);
  }
}
