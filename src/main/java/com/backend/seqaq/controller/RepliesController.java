package com.backend.seqaq.controller;


import com.backend.seqaq.entity.Answers;
import com.backend.seqaq.entity.Replies;
import com.backend.seqaq.service.RepliesService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/replies")
@CrossOrigin
@Api
public class RepliesController {
    @Autowired
    private RepliesService repliesService;

    @GetMapping("/findOneReply")
    public Replies findReply(@RequestParam("rid")Long rid) {
        return repliesService.findReply(rid);
    }
    @GetMapping("/findRepliesForAnswer")
    public List<Replies> findRepliesForAnswer(@RequestParam("aid")Long aid) {
        return repliesService.findByAid(aid);
    }
    @GetMapping("/findRepliesForReply")
    public List<Replies> findRepliesForReply(@RequestParam("rid")Long rid) {
        return repliesService.findByRid(rid);
    }
    @PostMapping("/replyForAnswer")
    public String replyForAnswer(@RequestParam("uid") Long uid,@RequestParam("did")Long did,@RequestParam("text")String text)
    {
        return repliesService.replyAnswers(uid,did,text);
    }
    @PostMapping("/replyForReply")
    public String replyForReply(@RequestParam("uid") Long uid,@RequestParam("did")Long did,@RequestParam("text")String text)
    {
        return repliesService.replyReplies(uid,did,text);
    }
    @PostMapping("/dislike")
    public String dislike(@RequestParam("rid") Long rid) {
        return repliesService.dislikeReplies(rid);
    }
    @PostMapping("/like")
    public String like(@RequestParam("rid") Long rid) {
        return repliesService.likeReplies(rid);
    }
}