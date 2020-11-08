package com.backend.seqaq.service.impl;

import com.backend.seqaq.dao.AnswersDao;
import com.backend.seqaq.dao.QuesDao;
import com.backend.seqaq.dao.RepliesDao;
import com.backend.seqaq.dao.UsersDao;
import com.backend.seqaq.entity.Answers;
import com.backend.seqaq.entity.Questions;
import com.backend.seqaq.entity.Replies;
import com.backend.seqaq.entity.Users;
import com.backend.seqaq.service.RepliesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class RepliesServiceImpl implements RepliesService {
    @Autowired
    private RepliesDao repliesDao;
    @Autowired
    private UsersDao usersDao;
    @Autowired
    private AnswersDao answersDao;


    public String replyAnswers(Long uid, Long did,String text) {
        Users users = usersDao.findById(uid);
        Answers answers = answersDao.findById(did);
        if( users == null || answers == null) return "Error";
        else {
            Replies replies = new Replies();
            replies.setDislike(0L);
            replies.setLike(0L);
            replies.setStatus(1);
            replies.setDid(did);
            replies.setDtype(0);
            replies.setUid(uid);
            replies.setAnswers(answers);
            replies.setUsers(users);
            replies.setReplies(null);
            Timestamp d = new Timestamp(System.currentTimeMillis());
            replies.setCtime(d);
            repliesDao.reply(replies);
            return "OK";
        }
    }
    public String replyReplies(Long uid, Long did,String text) {
        Users users = usersDao.findById(uid);
        Replies repliestmp = repliesDao.findReply(did);
        if( users == null || repliestmp == null) return "Error";
        else {
            Replies replies = new Replies();
            replies.setDislike(0L);
            replies.setLike(0L);
            replies.setStatus(1);
            replies.setDid(did);
            replies.setDtype(1);
            replies.setUid(uid);
            replies.setAnswers(null);
            replies.setUsers(users);
            replies.setReplies(repliestmp);
            Timestamp d = new Timestamp(System.currentTimeMillis());
            replies.setCtime(d);
            repliesDao.reply(replies);
            return "OK";
        }
    }
    public Replies findReply(Long rid){
        return repliesDao.findReply(rid);
    }
    public List<Replies> findByUid(Long uid){
        return repliesDao.findByUid(uid);
    }
    public List<Replies> findByAid(Long aid) {
        return repliesDao.findByAid(aid);
    }
    public List<Replies> findByRid(Long rid) {
        return repliesDao.findByRid(rid);
    }
    public String likeReplies(Long rid){
        Replies replies = repliesDao.findReply(rid);
        if (replies == null) return "Error";
        else {
            Long like = replies.getLike();
            replies.setLike(like+1);
            repliesDao.reply(replies);
            return "OK";
        }
    }
    public String dislikeReplies(Long rid){
        Replies replies = repliesDao.findReply(rid);
        if (replies == null) return "Error";
        else {
            Long dislike = replies.getDislike();
            replies.setDislike(dislike+1);
            repliesDao.reply(replies);
            return "OK";
        }
    }
}
