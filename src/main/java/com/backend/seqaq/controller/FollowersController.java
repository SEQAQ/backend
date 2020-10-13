package com.backend.seqaq.controller;


import com.backend.seqaq.entity.Followers;
import com.backend.seqaq.entity.Questions;
import com.backend.seqaq.service.FollowersService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/followers")
@CrossOrigin
@Api
public class FollowersController {
    @Autowired
    private FollowersService followersService;


    //查找关注的人
    @GetMapping("/findFollowed")
    public List<Followers> findFollowedByUid(@RequestParam("uid") Long uid){
        return followersService.findAllFollowedByUid(uid);
    }

    //查找粉丝
    @GetMapping("/findFans")
    public List<Followers> findFansByUid(@RequestParam("uid") Long uid){
        return followersService.findAllFollowerByUid(uid);
    }

    @PostMapping("/followSomeone")
    public String follow(@RequestParam("uid1") Long uid1,@RequestParam("uid2") Long uid2) {
        return followersService.addFollow(uid1,uid2);
    }

    @PostMapping("/unfollowSomeone")
    public String unfollow(@RequestParam("uid1") Long uid1,@RequestParam("uid2") Long uid2) {
        return followersService.delFollow(uid1,uid2);
    }
}
