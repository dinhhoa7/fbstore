package com.hoavd.fbstore.user.service.impl;


import com.hoavd.fbstore.user.entity.User;
import com.hoavd.fbstore.user.repository.UserRepository;
import com.hoavd.fbstore.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
  @Autowired
  private UserRepository userRepository;

  @Override
  public Page<User> getPageListUser(Pageable pageable) {
    return userRepository.getPageListUser(pageable);
  }

  @Override
  public User getUserById(long id) {
    return userRepository.findById(id);
  }

  @Override
  public User getByUsername(String username) {
    return userRepository.findByUsername(username);
  }

  @Override
  public User save(User user) {
    return userRepository.save(user);
  }

  @Override
  public void delete(long id) {
    userRepository.deleteById(id);
  }
}
