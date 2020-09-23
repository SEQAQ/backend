package com.backend.seqaq.service;

import com.backend.seqaq.entity.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UsersServiceTest {
    @Autowired
    private UsersService usersService;

    @Test
    void findById() {
    }

    @Test
    void findByAccount() {
    }

    @Test
    void register() {
        Users u = new Users();
        System.out.println(usersService.Register(u));
    }
}