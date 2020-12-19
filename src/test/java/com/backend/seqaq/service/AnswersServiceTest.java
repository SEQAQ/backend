package com.backend.seqaq.service;

import com.backend.seqaq.entity.Answers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("local")
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
    List<Answers> ans2 = answersService.findAnswersByQid(-1L);
    System.out.println(ans2);
    assertEquals(0,ans2.size());
  }
  @Test
  public void getAnswersByUid() {
    List<Answers> ans = answersService.findAnswersByUid(1L);
    System.out.println(ans);
    Answers ans0 = ans.get(0);
    assertNotNull(ans0);
    assertEquals(1L, ans0.getDetail().getAid());
    assertNotNull(ans0.getDetail().getMdText());
    assertNotEquals("", ans0.getDetail().getMdText());
    List<Answers> ans2 = answersService.findAnswersByUid(-1L);
    System.out.println(ans2);
    assertEquals(0,ans2.size());
  }
  @Test
  public void ban_unban() {
    answersService.banAnswers(3L);
    Answers answers = answersService.findAnswersById(3L);
    assertEquals(0,answers.getStatus());
    answersService.unbanAnswers(3L);
    Answers answers2 = answersService.findAnswersById(3L);
    assertEquals(1,answers2.getStatus());
  }

  @Test
  public void like_dislike() {
    Long likebefore = answersService.findAnswersById(3L).getLike();
    answersService.likeAnswers(3L);
    Long likeafter = answersService.findAnswersById(3L).getLike();
    assertEquals(1L,likeafter-likebefore);
    Long dlikebefore = answersService.findAnswersById(3L).getDislike();
    answersService.dislikeAnswers(3L);
    Long dlikeafter = answersService.findAnswersById(3L).getDislike();
    assertEquals(1L,dlikeafter-dlikebefore);
  }

  @Test
  public void add_delete() {
    String text1 = "测试测试";
    String text2 = "翻墙软件真好用";
    Long aid = Long.parseLong(answersService.addAnswers(1L,1L,text1));
    assertEquals("测试测试",answersService.findAnswersById(aid).getDetail().getMdText());
    String result = answersService.addAnswers(1L,1L,text2);
    answersService.deleteAnswers(aid);
    assertEquals(-1,answersService.findAnswersById(aid).getStatus());
    assertNotEquals("OK",result);
  }
  @Test
  public void edit(){
    String text = "edit test";
    answersService.editAnswers(1L,text);
    System.out.println(answersService.findAnswersById(1L).getDetail().getMdText());
    String text2 = "翻墙软件真好用";
    String ret = answersService.editAnswers(1L,text2);
    assertNotEquals("OK",ret);
  }
}
