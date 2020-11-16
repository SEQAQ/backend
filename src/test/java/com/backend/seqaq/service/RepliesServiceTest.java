package com.backend.seqaq.service;

import com.backend.seqaq.entity.Replies;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RepliesServiceTest {

  @Autowired private RepliesService repliesService;

  @Test
  void replyAnswers() {}

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
