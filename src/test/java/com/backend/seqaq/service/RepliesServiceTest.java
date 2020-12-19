package com.backend.seqaq.service;

import com.backend.seqaq.entity.Replies;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RepliesServiceTest {

  @Autowired private RepliesService repliesService;

  @Test
  @DirtiesContext
  void replyAnswers() {
    String expected = "Official sources say...";
    String ret = repliesService.replyAnswers(1L, 1L, expected);
    assertEquals("OK", ret);
    List<Replies> l = repliesService.findByAid(2L);
    assertEquals(expected, l.get(1).getContent().getContent());
  }

  @Test
  void replyReplies() {}

  @Test
  void findReply() {
    Replies reply = repliesService.findReply(1L);
    assertNotNull(reply);
    assertEquals("总之就是非常可爱", reply.getContent().getContent());
  }

  @Test
  void findByUid() {}

  @Test
  void findByQid() {}

  @Test
  void findByRid() {}
}
