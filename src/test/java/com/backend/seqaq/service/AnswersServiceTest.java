package com.backend.seqaq.service;

import com.backend.seqaq.entity.Answers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class AnswersServiceTest {
  @Autowired AnswersService answersService;

  @Test
  public void getAnswersByQid() {
    List<Answers> ans = answersService.findAnswersByQid(1L);
    System.out.println(ans);
    Answers ans0 = ans.get(0);
    assertNotNull(ans0);
    assertEquals(1L, ans0.getDetail().getAid());
    assertNotNull(ans0.getDetail().getMdText());
    assertNotEquals("", ans0.getDetail().getMdText());
  }
}
