package com.backend.seqaq.controller;

import com.backend.seqaq.entity.Answers;
import com.backend.seqaq.service.AnswersService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/answers")
@CrossOrigin
@Api
public class AnswersController {
  @Autowired private AnswersService answersService;

  @GetMapping("/findByAid")
  public Answers findAnswer(@RequestParam("aid") Long aid) {
    return answersService.findAnswersByid(aid);
  }

  @PostMapping("/addAnswer")
  public String addAnswer(
      @RequestParam("uid") Long uid,
      @RequestParam("qid") Long qid,
      @RequestParam("text") String text) {
    return answersService.addAnswers(uid, qid, text);
  }

  @PostMapping("/editAnswer")
  public String editAnswer(@RequestParam("aid") Long aid, @RequestParam("text") String text) {
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
