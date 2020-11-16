package com.backend.seqaq.service;

import com.backend.seqaq.entity.Users;

public interface UsersService {
  Users findById(Long id);

  Users findByAccount(String account);

  String register(Users u);

  String banUser(Long uid);

  String unbanUser(Long uid);

  String login(String account, String password);
}
