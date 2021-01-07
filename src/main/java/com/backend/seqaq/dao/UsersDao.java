package com.backend.seqaq.dao;

import com.backend.seqaq.entity.Users;
import com.backend.seqaq.util.exception.RegistrationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UsersDao {
  Users findById(Long id);

  Users findByAccount(String account);

  Users register(Users u) throws RegistrationException;

  List<Users> findAllByUnameContaining(String text);

  Users saveUser(Users user);

  Page<Users> findAll(Pageable pageable);
}
