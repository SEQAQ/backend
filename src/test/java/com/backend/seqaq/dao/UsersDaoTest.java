package com.backend.seqaq.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class UsersDaoTest {
    @Autowired
    private UsersDao usersDao;
    @Test
    void findByAccount() {
        System.out.println(usersDao.findByAccount("nihao"));
    }
    @Test
    void findbyid(){
        System.out.println(usersDao.findById((long)2));
    }
}
