package com.backend.seqaq.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserAndQuesServiceTest {

  @Autowired private UserAndQuesService userAndQuesService;

  @Test
  void existsByUidAndQid() {
    //        System.out.println(1);
    //        System.out.println(userAndQuesService.existsByUidAndQid(1L,1L));
  }
}
