package com.backend.seqaq.controller;

import com.backend.seqaq.entity.Questions;
import com.backend.seqaq.service.HotService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hot")
@CrossOrigin
@Api
public class HotController {
  @Autowired private HotService hotService;

  @PostMapping("/add")
  public void add(@RequestParam("qid") Long qid, @RequestParam("uid") Long uid) {
    hotService.add(qid, uid);
    return;
  }

  @GetMapping("/getTop10")
  public List<Questions> getTop10(@RequestParam("option") int option) {
    return hotService.getTop10(option);
  }
}
