package com.backend.seqaq.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@ActiveProfiles("test")
class RecommendServiceTest {
    @Autowired private RecommendService recommendService;

    @Test
    void recommendByfriends() {
        recommendService.recommendByfriends(1L);
    }

    @Test
    void recommendByQues() {
        recommendService.recommendByQues(1L);
    }

    @Test
    void recommendQues() {
        recommendService.recommendQues(1L);
    }
}