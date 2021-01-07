package com.backend.seqaq.repository;

import com.backend.seqaq.entity.Users;
import org.h2.engine.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users, Long> {
  Users findByAccount(String account);
  List<Users> findAllByUnameContaining(String text);
}
