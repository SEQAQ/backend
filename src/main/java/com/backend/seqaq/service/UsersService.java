package com.backend.seqaq.service;

import com.backend.seqaq.entity.Users;

public interface UsersService {
    Users FindById(Long id);
    Users FindByAccount(String account);
    String Register(Users u);
}
