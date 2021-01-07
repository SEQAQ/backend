package com.backend.seqaq.service;

import com.backend.seqaq.entity.UserBean;
import com.backend.seqaq.entity.Users;
import com.backend.seqaq.util.exception.RegistrationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UsersService {
  Users findById(Long id);

  Users findByAccount(String account);

  Users register(Users u) throws RegistrationException;

  String banUser(Long uid);

  String unbanUser(Long uid);

  String login(String account, String password);

  String checkStatus(String account);

  void activate(Users user);

  UserBean getUser(String username);

  Page<Users> findAll(Pageable pageable);

  List<Users> findAll();

  Long editUser(Users users);
}
