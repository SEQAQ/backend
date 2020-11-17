package com.backend.seqaq.controller;

import com.alibaba.fastjson.JSONObject;
import com.backend.seqaq.entity.Answers;
import com.backend.seqaq.service.AnswersService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/answers")
@CrossOrigin
@Api
public class AnswersController {

  @Autowired private AnswersService answersService;

  @GetMapping("/findByAid")
  public Answers findAnswer(@RequestParam("aid") Long aid) {
    return answersService.findAnswersById(aid);
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
  public String addAnswer(@RequestBody JSONObject jsonObject) {
    Long uid = jsonObject.getLong("uid");
    Long qid = jsonObject.getLong("qid");
    String text = jsonObject.getString("text");
    return answersService.addAnswers(uid, qid, text);
  }

  @PostMapping("/editAnswer")
  public String editAnswer(@RequestBody JSONObject jsonObject) {
    String text = jsonObject.getString("text");
    Long aid = jsonObject.getLong("aid");
    return answersService.editAnswers(aid, text);
  }

  @PostMapping("/ban")
  public String ban(@RequestParam("aid") Long aid) {
    return answersService.banAnswers(aid);
  }

  @PostMapping("/unban")
  public String unban(@RequestParam("aid") Long aid) {
    return answersService.unbanAnswers(aid);
  }

  @PostMapping("/delete")
  public String delete(@RequestParam("aid") Long aid) {
    return answersService.deleteAnswers(aid);
  }

  @PostMapping("/dislike")
  public String dislike(@RequestParam("aid") Long aid) {
    return answersService.dislikeAnswers(aid);
  }

  @PostMapping("/like")
  public String like(@RequestParam("aid") Long aid) {
    return answersService.likeAnswers(aid);
  }
}
