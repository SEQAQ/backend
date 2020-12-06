package com.backend.seqaq.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("local")
class QueryServiceTest {

    @Autowired
    private QueryService queryService;
    @Test
    void queryForQuesByContent() {
        System.out.println(queryService.queryForQuesByContent("123"));
    }
}