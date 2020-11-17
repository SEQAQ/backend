package com.backend.seqaq.service;

import com.backend.seqaq.entity.Questions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class QuesServiceTest {
  @Autowired private QuesService quesService;

  @Test
  void findByUid() {}

  @Test
  void findByQid() {
    Questions q = quesService.findById(1L);
    assertNotNull(q);
    System.out.println(q);
  }

  @Test
  void createQues() {
    System.out.println(quesService.createQues("??", "??", 5L));
  }

  @Test
  void editQues() {}

  @Test
  void banQues() {}

  @Test
  void unbanQues() {}

  @Test
  void delQues() {}
}
