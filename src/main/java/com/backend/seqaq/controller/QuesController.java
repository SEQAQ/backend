package com.backend.seqaq.controller;

import com.alibaba.fastjson.JSONObject;
import com.backend.seqaq.entity.Questions;
import com.backend.seqaq.service.QuesService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ques")
@CrossOrigin
@Api
public class QuesController {

  @Autowired private QuesService quesService;

  @GetMapping("/findByUid")
  public List<Questions> findByUid(@RequestParam("uid") Long uid) {
    System.out.println(quesService.findByUid(uid));
    return quesService.findByUid(uid);
  }

  @GetMapping("/findByQid")
  public Questions findById(@RequestParam("qid") Long qid) {
    Questions ques = quesService.findById(qid);
    System.out.println(ques);
    return ques;
  }

  @PostMapping("/createQues")
  public void create(@RequestBody JSONObject jsonObject) {
    String title = jsonObject.getString("title");
    String tag = jsonObject.getString("tag");
    Long uid = jsonObject.getLong("uid");
    quesService.createQues(title, tag, uid);
  }

  @PostMapping("/new")
  public void createWithDetails(@RequestBody JSONObject test) {
    System.out.println(test);
    quesService.createQuestion(test);
  }

  @PostMapping("/editQues")
  public void edit(@RequestParam("qid") Long qid, @RequestParam("text") String text) {
    quesService.editQues(qid, text);
  }

  @PostMapping("/banQues")
  public void ban(@RequestParam("qid") Long qid) {
    quesService.banQues(qid);
  }

  @PostMapping("/unbanQues")
  public void unban(@RequestParam("qid") Long qid) {
    quesService.unbanQues(qid);
  }

  @PostMapping("/delQues")
  public void del(@RequestParam("qid") Long qid) {
    quesService.delQues(qid);
  }
}
