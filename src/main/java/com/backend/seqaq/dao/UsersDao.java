package com.backend.seqaq.dao;

import com.backend.seqaq.entity.Users;

public interface UsersDao {
    Users FindById(Long id);
    Users FindByAccount(String account);
    String Register(Users u);

}
