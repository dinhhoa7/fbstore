package com.hoavd.fbstore.user.service;


import com.hoavd.fbstore.user.entity.Admin;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdminService {
  Page<Admin> getPageListAdmin(Pageable pageable);

  Admin getAdminById(long id);

  Admin getByUsername(String username);

  Admin getByIdAndUsername(long id, String username);

  Admin save(Admin admin);

  void delete(long id);
}
