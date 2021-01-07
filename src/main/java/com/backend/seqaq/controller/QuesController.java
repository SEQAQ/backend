package com.backend.seqaq.controller;

import com.alibaba.fastjson.JSONObject;
import com.backend.seqaq.config.JWTUtil;
import com.backend.seqaq.entity.Questions;
import com.backend.seqaq.entity.Users;
import com.backend.seqaq.service.QuesService;
import com.backend.seqaq.service.UsersService;

import com.backend.seqaq.util.Message;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
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
  public String createWithDetails(@RequestBody JSONObject data) {
    return quesService.createQuestion(data);
  }

  @PostMapping("/editQues")
  @RequiresAuthentication
  public Message<String> edit(
      @RequestHeader("Authorization") String token, @RequestBody JSONObject data) {
    String account = JWTUtil.getUsername(token);
    Users user = usersService.findByAccount(account);
    if (user == null) return new Message<>(233, "Authentication failed!");
    Long qid = data.getLong("qid");
    String title = data.getString("title");
    String mdText = data.getString("detail");
    Questions ques = quesService.findById(qid);
    // not a admin, nor the ask-er
    if (ques.getUsers() == null
        || (!ques.getUsers().getRole().equals("admin")
            && !ques.getUsers().getUid().equals(user.getUid())))
      return new Message<>(666, "Access denied for current user");
    // within 24hours
    Timestamp ctimeAfterOneDay = ques.getCtime();
    ctimeAfterOneDay.setTime(ctimeAfterOneDay.getTime() + 60 * 24 * 24);
    Timestamp now = new Timestamp(System.currentTimeMillis());
    if (!ques.getUsers().getRole().equals("admin") && !now.before(ctimeAfterOneDay))
      return new Message<>(30, "Questions can only be edited within 24hours");
    quesService.editQues(qid, title, mdText);
    return new Message<>("OK");
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

  @GetMapping("/delQues")
  @RequiresAuthentication
  public void del(@RequestParam("qid") Long qid) {
    quesService.delQues(qid);
  }

  @PostMapping("/closeQues")
  public void close(@RequestHeader("Authorization") String token, @RequestParam("qid") Long qid) {
    String account = JWTUtil.getUsername(token);
    Users user = usersService.findByAccount(account);
    Questions questions = quesService.findById(qid);
    if (questions.getUid() != user.getUid()) return;

    quesService.close(qid);
  }

  @GetMapping("/openQues")
  public void open(@RequestParam("qid") Long qid) {
    quesService.unbanQues(qid);
  }
}
