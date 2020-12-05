package com.backend.seqaq.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ActiveProfiles("local")
class QuesDaoTest {
    @Autowired
    private QuesDao quesDao;
    @Test
    void findAllByTitleContaining() {
        System.out.println(quesDao.findAllByTitleContaining("为什么"));
    }
}