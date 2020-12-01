package com.backend.seqaq.service;

import com.backend.seqaq.entity.Users;
import com.backend.seqaq.util.exception.RegistrationException;

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

  String generateJwtToken(String account);

  Users getJwtTokenInfo(String account);

  void deleteLoginInfo(String account);

  Users getUserInfo(String account);

  List<String> getUserRoles(Long uid);
}
