package com.backend.seqaq.dao;

import com.backend.seqaq.entity.Questions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test")
public class QuesDaoTest {
  @Autowired QuesDao quesDao;

  @Test
  void TestFindQuestionByDetailContaining() {
    List<Questions> quesList = quesDao.findAllByDetailContaining("RA");
    assertNotNull(quesList);
    assertTrue(quesList.size() > 0);
  }
}
