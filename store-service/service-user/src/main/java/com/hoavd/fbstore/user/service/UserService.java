package com.hoavd.fbstore.user.service;

import com.hoavd.fbstore.user.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
  Page<User> getPageListUser(Pageable pageable);

  User getUserById(long id);

  User getByUsername(String email);

  User save(User user);

  void delete(long id);
}
