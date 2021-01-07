package com.backend.seqaq.service;

import com.alibaba.fastjson.JSONObject;
import com.backend.seqaq.entity.Questions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class QuesServiceTest {
  @Autowired private QuesService quesService;

  @Test
  void findByUid() {
    List<Questions> q = quesService.findByUid(1L);
    Questions questions = q.get(0);
    assertNotNull(questions);
    assertEquals(1L, questions.getUid());
    q = quesService.findByUid(-1L);
    assertNull(q);
  }

  @Test
  void findByQid() {
    Questions q = quesService.findById(1L);
    assertNotNull(q);
    assertEquals(1L, q.getQid());
  }

  @Test
  void addQues() {
    String textnormal = "如题";
    String abnormal = "傻逼";
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("uid", "1");
    jsonObject.put("tag", "");
    jsonObject.put("title", "单元测试写不完了怎么办");
    jsonObject.put("detail", textnormal);
    System.out.println(jsonObject.getLong("uid"));
    String ret = quesService.createQuestion(jsonObject);
    Long qid = Long.parseLong(ret);
    Questions questions = quesService.findById(qid);
    assertEquals(textnormal, questions.getDetail().getDetail());
    quesService.delQues(qid);
    assertEquals(-1, quesService.findById(qid).getStatus());
    JSONObject jsonObject0 = new JSONObject();
    jsonObject0.put("uid", "1");
    jsonObject0.put("tag", "毛");
    jsonObject0.put("title", "蛤");
    jsonObject0.put("detail", abnormal);
    ret = quesService.createQuestion(jsonObject0);
    System.out.println(ret);
    boolean flag = ret.startsWith("问题");
    assertEquals(true, flag);
    JSONObject jsonObject1 = new JSONObject();
    jsonObject1.put("uid", "1");
    jsonObject1.put("tag", "毛泽东");
    jsonObject1.put("title", "你好");
    jsonObject1.put("detail", textnormal);
    ret = quesService.createQuestion(jsonObject1);
    System.out.println(ret);
    flag = ret.startsWith("问题");
    assertEquals(true, flag);
    JSONObject jsonObject2 = new JSONObject();
    jsonObject2.put("uid", "1");
    jsonObject2.put("tag", "啊这");
    jsonObject2.put("title", "毛泽东");
    jsonObject2.put("detail", textnormal);
    System.out.println(ret);
    ret = quesService.createQuestion(jsonObject2);
    flag = ret.startsWith("问题");
    assertEquals(true, flag);
  }

  @Test
  void editQues() {
    String edit = "edit test";
    String abnormal = "艹你妈";
    String ret = quesService.editQues(1L, edit);
    Long qid = Long.parseLong(ret);
    Questions q = quesService.findById(qid);
    assertEquals(1L, q.getQid());
    assertEquals(edit, q.getDetail().getDetail());
    ret = quesService.editQues(1L, abnormal);
    assertNotEquals("1", ret);
  }

  @Test
  void ban_unBan() {
    quesService.banQues(1L);
    Questions questions = quesService.findById(1L);
    assertEquals(0, questions.getStatus());
    quesService.unbanQues(1L);
    Questions questions1 = quesService.findById(1L);
    assertEquals(1, questions1.getStatus());
  }

  @Test
  void tmp() {
    System.out.println(quesService.findById(1L).getCtime());
  }
}
