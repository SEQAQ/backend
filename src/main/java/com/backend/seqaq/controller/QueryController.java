package com.backend.seqaq.controller;

import com.backend.seqaq.entity.Questions;
import com.backend.seqaq.entity.Users;
import com.backend.seqaq.service.QueryService;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/query")
@CrossOrigin
@Api
public class QueryController {
  @Autowired private QueryService queryService;

  @GetMapping("/users")
  public List<Users> queryForUsers(@RequestParam("nickname") String nickname) {
    return queryService.queryForUsers(nickname);
  }

  @GetMapping("/ques/tag")
  @RequiresAuthentication
  public List<Questions> queryForQuesByTag(@RequestParam("tag") String tag) {
    return queryService.queryForQuesByTag(tag);
  }

  @GetMapping("/ques/title")
  public List<Questions> queryForQuesByTitle(@RequestParam("title") String title) {
    return queryService.queryForQuesByTitle(title);
  }

  @GetMapping("/ques/content")
  public List<Questions> queryForQuesByContent(@RequestParam("content") String content) {
    return queryService.queryForQuesByContent(content);
  }
}
