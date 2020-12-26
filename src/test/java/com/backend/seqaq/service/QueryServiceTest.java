package com.backend.seqaq.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class QueryServiceTest {

  @Autowired private QueryService queryService;

  @Test
  void queryForQues() {
    System.out.println(queryService.queryForQuesByContent("123"));
    System.out.println(queryService.queryForQuesByTag("123"));
    System.out.println(queryService.queryForQuesByTitle("123"));
    System.out.println(queryService.queryForUsers("123"));
  }
}
