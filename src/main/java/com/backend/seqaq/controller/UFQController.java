package com.backend.seqaq.controller;

import com.backend.seqaq.entity.UserAndQues;
import com.backend.seqaq.service.UserAndQuesService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/UFQ")
@CrossOrigin
@Api
public class UFQController {
  @Autowired private UserAndQuesService userAndQuesService;

  @GetMapping("/findByUid")
  public List<UserAndQues> findByUid(@RequestParam("uid") Long uid) {
    return userAndQuesService.findAllQuesByUid(uid);
  }

  // 查找粉丝
  @GetMapping("/findByQid")
  public List<UserAndQues> findByQid(@RequestParam("qid") Long qid) {
    return userAndQuesService.findAllUsersByQid(qid);
  }

  @PostMapping("/followSomeQues")
  public String follow(@RequestParam("uid") Long uid, @RequestParam("qid") Long qid) {
    return userAndQuesService.addFollow(uid, qid);
  }

  @PostMapping("/unfollowSomeQues")
  public String unfollow(@RequestParam("uid") Long uid, @RequestParam("qid") Long qid) {
    return userAndQuesService.delFollow(uid, qid);
  }
}
