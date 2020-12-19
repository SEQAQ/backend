package com.backend.seqaq.service;

import com.backend.seqaq.entity.Replies;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RepliesServiceTest {

  @Autowired private RepliesService repliesService;

  @Test
  void replyAnswers() {
    String expected = "Official sources say...";
    String ret = repliesService.replyAnswers(1L, 1L, expected);
    Long rid = Long.parseLong(ret);
    Replies r = repliesService.findReply(rid);
    assertEquals(expected,r.getContent().getContent());
    assertEquals(0,r.getDtype());
    ret = repliesService.replyReplies(1L, 1L, expected);
    rid = Long.parseLong(ret);
    r = repliesService.findReply(rid);
    assertEquals(expected,r.getContent().getContent());
    assertEquals(1,r.getDtype());
  }

  @Test
  void replyForExamining() {
    String expected = "翻墙软件真好用";
    String ret = repliesService.replyAnswers(1L, 1L, expected);
    assertNotEquals(expected,ret);
    ret = repliesService.replyReplies(1L, 1L, expected);
    assertNotEquals(expected,ret);
  }
  @Test
  void findByUid() {
    List<Replies> l = repliesService.findByUid(1L);
    Replies replies = l.get(0);
    assertNotNull(replies);
    assertEquals(1L, replies.getUid());
    List<Replies> rep2 = repliesService.findByUid(-1L);
    System.out.println(rep2);
    assertEquals(0,rep2.size());
  }

  @Test
  void findByAid_Rid() {
    List<Replies> l = repliesService.findByAid(1L);
    Replies replies = l.get(0);
    assertNotNull(replies);
    assertEquals(0, replies.getDtype());
    assertEquals(1L,replies.getDid());
    List<Replies> rep2 = repliesService.findByAid(-1L);
    System.out.println(rep2);
    assertEquals(0,rep2.size());
    l = repliesService.findByRid(1L);
    replies = l.get(0);
    assertNotNull(replies);
    assertEquals(1, replies.getDtype());
    assertEquals(1L,replies.getDid());
    rep2 = repliesService.findByRid(-1L);
    System.out.println(rep2);
    assertEquals(0,rep2.size());
  }
  @Test
  void like_dis() {
    Long likebefore = repliesService.findReply(1L).getLike();
    repliesService.likeReplies(1L);
    Long likeafter = repliesService.findReply(1L).getLike();
    assertEquals(1L,likeafter-likebefore);
    Long dlikebefore = repliesService.findReply(1L).getDislike();
    repliesService.dislikeReplies(1L);
    Long dlikeafter = repliesService.findReply(1L).getDislike();
    assertEquals(1L,dlikeafter-dlikebefore);
  }
}
