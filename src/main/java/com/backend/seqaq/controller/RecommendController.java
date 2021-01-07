package com.backend.seqaq.controller;


import com.backend.seqaq.entity.Questions;
import com.backend.seqaq.entity.Users;
import com.backend.seqaq.service.RecommendService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ques")
@CrossOrigin
@Api
public class RecommendController {
    @Autowired private RecommendService recommendService;

    @GetMapping("/recommendUsersByFriends")
    public List<Users> recommendByFriends(@RequestParam("uid") Long uid)
    {
        return recommendService.recommendByfriends(uid);
    }
    @GetMapping("/recommendUsersByQues")
    public List<Users> recommendUsersByQues(@RequestParam("uid") Long uid)
    {
        return recommendService.recommendByQues(uid);
    }
    @GetMapping("/recommendQues")
    public List<Questions> recommendQues(@RequestParam("uid") Long uid)
    {
        return recommendService.recommendQues(uid);
    }
}
