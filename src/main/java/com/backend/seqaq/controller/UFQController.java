package com.backend.seqaq.controller;

import com.backend.seqaq.entity.UserAndQues;
import com.backend.seqaq.service.UserAndQuesService;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
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
  @RequiresAuthentication
  public String follow(@RequestParam("uid") Long uid, @RequestParam("qid") Long qid) {
    return userAndQuesService.addFollow(uid, qid);
  }

  @PostMapping("/unfollowSomeQues")
  @RequiresAuthentication
  public String unfollow(@RequestParam("uid") Long uid, @RequestParam("qid") Long qid) {
    return userAndQuesService.delFollow(uid, qid);
  }

  @PostMapping("/isfollowed")
  public String isFollowed(@RequestParam("uid") Long uid, @RequestParam("qid") Long qid) {
    boolean flag = userAndQuesService.existsByUidAndQid(uid, qid);
    if (flag) return "Yes";
    else return "No";
  }
}
