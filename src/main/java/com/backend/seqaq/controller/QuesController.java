package com.backend.seqaq.controller;

import com.alibaba.fastjson.JSONObject;
import com.backend.seqaq.entity.Questions;
import com.backend.seqaq.service.QuesService;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
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


  @PostMapping("/new")
  @RequiresAuthentication
  public String createWithDetails(@RequestBody JSONObject test) {
    System.out.println(test);
    return quesService.createQuestion(test);
  }

  @PostMapping("/editQues")
  @RequiresAuthentication
  public void edit(@RequestParam("qid") Long qid, @RequestParam("text") String text) {
    quesService.editQues(qid, text);
  }

  @PostMapping("/banQues")
  @RequiresRoles("admin")
  public void ban(@RequestParam("qid") Long qid) {
    quesService.banQues(qid);
  }

  @PostMapping("/unbanQues")
  @RequiresRoles("admin")
  public void unban(@RequestParam("qid") Long qid) {
    quesService.unbanQues(qid);
  }

  @PostMapping("/delQues")
  @RequiresAuthentication
  public void del(@RequestParam("qid") Long qid) {
    quesService.delQues(qid);
  }
}
