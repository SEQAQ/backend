package com.backend.seqaq.controller;


import com.backend.seqaq.entity.Questions;
import com.backend.seqaq.entity.Users;
import com.backend.seqaq.service.QuesService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ques")
@CrossOrigin
@Api
public class QuesController {
    @Autowired
    private QuesService quesService;

    @GetMapping("/findByUid")
    public List<Questions> findByUid(@RequestParam("uid") Long uid){
        System.out.println(quesService.findByUid(uid));
        return quesService.findByUid(uid);
    }
    @GetMapping("/findById")
    public Questions findById(@RequestParam("qid") Long qid){
        return quesService.findById(qid);
    }
    @PostMapping("/createQues")
    public void create(@RequestParam("title") String title,@RequestParam("tag") String tag,@RequestParam("uid") Long uid){
        quesService.createQues(title, tag, uid);
    }

    @PostMapping("/editQues")
    public void edit(@RequestParam("qid") Long qid,@RequestParam("text") String text){
        quesService.editQues(qid,text);
    }
    @PostMapping("/banQues")
    public void ban(@RequestParam("qid") Long qid){
        quesService.banQues(qid);
    }
    @PostMapping("/unbanQues")
    public void unban(@RequestParam("qid") Long qid){
        quesService.unbanQues(qid);
    }
    @PostMapping("/delQues")
    public void del(@RequestParam("qid") Long qid){
        quesService.delQues(qid);
    }
}
