package com.backend.seqaq.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class QuesServiceTest {
    @Autowired
    private QuesService quesService;
    @Test
    void findByUid() {
    }

    @Test
    void createQues() {
        System.out.println(quesService.createQues("??","??",5L));
    }

    @Test
    void editQues() {
    }

    @Test
    void banQues() {
    }

    @Test
    void unbanQues() {
    }

    @Test
    void delQues() {
    }
}