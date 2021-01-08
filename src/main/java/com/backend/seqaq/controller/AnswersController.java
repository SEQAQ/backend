package com.backend.seqaq.controller;

import com.alibaba.fastjson.JSONObject;
import com.backend.seqaq.entity.Answers;
import com.backend.seqaq.repository.Like_recordRepository;
import com.backend.seqaq.service.AnswersService;
import com.backend.seqaq.util.Message;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/answers")
@CrossOrigin
@Api
public class AnswersController {

  @Autowired private AnswersService answersService;
  @Autowired private Like_recordRepository like_recordRepository;

  @GetMapping("/findByAid")
  public Message<Answers> findAnswer(@RequestParam("aid") Long aid) {
    return new Message<>(answersService.findAnswersById(aid));
  }

  @GetMapping("/findByUid")
  public List<Answers> findAnswersByUid(@RequestParam("uid") Long uid) {
    return answersService.findAnswersByUid(uid);
  }

  @GetMapping("/findByQid")
  public List<Answers> findByQid(@RequestParam("qid") Long qid) {
    return answersService.findAnswersByQid(qid);
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

  @GetMapping("/delete")
  @RequiresAuthentication
  public String delete(@RequestParam("aid") Long aid) {
    return answersService.deleteAnswers(aid);
  }

  @GetMapping("/dislike")
  @RequiresAuthentication
  public String dislike(@RequestParam("aid") Long aid) {
    return answersService.dislikeAnswers(aid);
  }

  @GetMapping("/undislike")
  @RequiresAuthentication
  public String undislike(@RequestParam("aid") Long aid) {
    return answersService.undislikeAnswers(aid);
  }

  @GetMapping("/like")
  @RequiresAuthentication
  public String like(@RequestParam("aid") Long aid, @RequestParam("uid") Long uid) {
    return answersService.likeAnswers(aid, uid);
  }

  @GetMapping("/unlike")
  @RequiresAuthentication
  public String undlike(@RequestParam("aid") Long aid, @RequestParam("uid") Long uid) {
    return answersService.unlikeAnswers(aid, uid);
  }

  @GetMapping("/islike")
  @RequiresAuthentication
  public boolean islike(@RequestParam("aid") Long aid, @RequestParam("uid") Long uid) {
    return like_recordRepository.existsByAidAndUid(aid, uid);
  }
}
