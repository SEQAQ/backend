package com.backend.seqaq.controller;

import com.alibaba.fastjson.JSONObject;
import com.backend.seqaq.config.JWTUtil;
import com.backend.seqaq.entity.Answers;
import com.backend.seqaq.entity.AnswerwithTag;
import com.backend.seqaq.entity.Users;
import com.backend.seqaq.repository.Like_recordRepository;
import com.backend.seqaq.service.AnswersService;
import com.backend.seqaq.service.UsersService;
import com.backend.seqaq.util.Message;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/answers")
@CrossOrigin
@Api
public class AnswersController {

  @Autowired private AnswersService answersService;
  @Autowired private Like_recordRepository like_recordRepository;
  @Autowired private UsersService usersService;

  @GetMapping("/findByAid")
  public Message<Answers> findAnswer(@RequestParam("aid") Long aid) {
    return new Message<>(answersService.findAnswersById(aid));
  }

  @GetMapping("/findByUidLogin")
  public List<AnswerwithTag> findAnswersByUidLogin(
          @RequestHeader("Authorization") String token,@RequestParam("uid") Long uid) {
    String account = JWTUtil.getUsername(token);
    Users user = usersService.findByAccount(account);
    Long tmpuid = user.getUid();
    List<Answers> list = answersService.findAnswersByQid(uid);
    List<AnswerwithTag> al = new ArrayList<>();
    for (int i = 0; i < list.size(); ++i) {
      Answers answers = list.get(i);
      AnswerwithTag a = new AnswerwithTag();
      a.setAnswers(answers);
      a.setTag(like_recordRepository.existsByAidAndUid(answers.getAid(), tmpuid));
      al.add(i, a);
    }
    return al;
  }

  @GetMapping("/findByUid")
  public List<Answers> findAnswersByUid(@RequestParam("uid") Long uid){
    return answersService.findAnswersByUid(uid);
  }

  @GetMapping("/findByQid")
  public List<Answers> findByQid(@RequestParam("qid") Long qid) {
    return answersService.findAnswersByQid(qid);
  }

  @GetMapping("/findByQidLogin")
  public List<AnswerwithTag> findByQid(
          @RequestHeader("Authorization") String token, @RequestParam("qid") Long qid) {
    String account = JWTUtil.getUsername(token);
    Users user = usersService.findByAccount(account);
    Long uid = user.getUid();
    List<Answers> list = answersService.findAnswersByQid(qid);
    List<AnswerwithTag> al = new ArrayList<>();
    for (int i = 0; i < list.size(); ++i) {
      Answers answers = list.get(i);
      AnswerwithTag a = new AnswerwithTag();
      a.setAnswers(answers);
      a.setTag(like_recordRepository.existsByAidAndUid(answers.getAid(), uid));
      al.add(i, a);
    }
    return al;
  }


  @PostMapping("/addAnswer")
  @RequiresAuthentication
  public String addAnswer(@RequestBody JSONObject jsonObject) {
    Long uid = jsonObject.getLong("uid");
    Long qid = jsonObject.getLong("qid");
    String text = jsonObject.getString("text");
    return answersService.addAnswers(uid, qid, text);
  }

  @PostMapping("/editAnswer")
  @RequiresAuthentication
  public String editAnswer(@RequestBody JSONObject jsonObject) {
    String text = jsonObject.getString("text");
    Long aid = jsonObject.getLong("aid");
    return answersService.editAnswers(aid, text);
  }

  @PostMapping("/ban")
  @RequiresRoles("admin")
  public String ban(@RequestParam("aid") Long aid) {
    return answersService.banAnswers(aid);
  }

  @PostMapping("/unban")
  @RequiresRoles("admin")
  public String unban(@RequestParam("aid") Long aid) {
    return answersService.unbanAnswers(aid);
  }

  @PostMapping("/delete")
  @RequiresAuthentication
  public String delete(@RequestParam("aid") Long aid) {
    return answersService.deleteAnswers(aid);
  }

  @PostMapping("/dislike")
  @RequiresAuthentication
  public String dislike(@RequestParam("aid") Long aid) {
    return answersService.dislikeAnswers(aid);
  }

  @PostMapping("/undislike")
  @RequiresAuthentication
  public String undislike(@RequestParam("aid") Long aid) {
    return answersService.undislikeAnswers(aid);
  }

  @PostMapping("/like")
  @RequiresAuthentication
  public String like(@RequestParam("aid") Long aid, @RequestParam("uid") Long uid) {
    return answersService.likeAnswers(aid, uid);
  }

  @PostMapping("/unlike")
  @RequiresAuthentication
  public String undlike(@RequestParam("aid") Long aid, @RequestParam("uid") Long uid) {
    return answersService.unlikeAnswers(aid, uid);
  }

  @PostMapping("/islike")
  @RequiresAuthentication
  public boolean islike(@RequestParam("aid") Long aid, @RequestParam("uid") Long uid) {
    return like_recordRepository.existsByAidAndUid(aid, uid);
  }
}
