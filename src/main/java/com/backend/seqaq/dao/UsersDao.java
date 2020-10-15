package com.backend.seqaq.dao;

import com.backend.seqaq.entity.Users;

public interface UsersDao {
    Users findById(Long id);
    Users findByAccount(String account);
    String register(Users u);

}
