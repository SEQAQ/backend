package com.backend.seqaq.controller;

import com.alibaba.fastjson.JSONObject;
import com.backend.seqaq.config.JWTUtil;
import com.backend.seqaq.entity.Questions;
import com.backend.seqaq.entity.Users;
import com.backend.seqaq.service.QuesService;
import com.backend.seqaq.service.UsersService;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ques")
@CrossOrigin
@Api
public class QuesController {
  @Autowired private QuesService quesService;
  @Autowired private UsersService usersService;

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

  @GetMapping("/findAllByPage")
  public Page<Questions> findAll(
      @RequestParam("page") Integer page, @RequestParam("size") Integer size) {
    Pageable pageable = PageRequest.of(page, size);
    return quesService.findAll(pageable);
  }

  @GetMapping("/findAll")
  public List<Questions> findAll() {
    return quesService.findAll();
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

  @PostMapping("/closeQues")
  public void close(@RequestHeader("Authorization") String token,@RequestParam("qid") Long qid){
    String account = JWTUtil.getUsername(token);
    Users user = usersService.findByAccount(account);
    Questions questions = quesService.findById(qid);
    if (questions.getUid()!= user.getUid()) return;
    quesService.close(qid);
  }
  @PostMapping("/openQues")
  public void open(@RequestParam("qid") Long qid){
    quesService.unbanQues(qid);
  }
}
