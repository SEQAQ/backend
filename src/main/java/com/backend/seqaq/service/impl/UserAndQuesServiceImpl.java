package com.backend.seqaq.service.impl;

import com.backend.seqaq.dao.QuesDao;
import com.backend.seqaq.dao.UserAndQuesDao;
import com.backend.seqaq.dao.UsersDao;
import com.backend.seqaq.entity.Questions;
import com.backend.seqaq.entity.UserAndQues;
import com.backend.seqaq.entity.Users;
import com.backend.seqaq.service.UserAndQuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAndQuesServiceImpl implements UserAndQuesService {
    @Autowired
    private UsersDao usersDao;
    @Autowired
    private QuesDao quesDao;
    @Autowired
    private UserAndQuesDao userAndQuesDao;

    public List<UserAndQues> findAllQuesByUid(Long uid){
        return userAndQuesDao.findAllQuesByUid(uid);
    }
    public List<UserAndQues> findAllUsersByQid(Long qid){
        return userAndQuesDao.findAllUsersByQid(qid);
    }
    public String delFollow(Long uid,Long qid){
        userAndQuesDao.delFollow(uid,qid);
        return "OK";
    }
    public String addFollow(Long uid,Long qid){
        Users users = usersDao.findById(uid);
        Questions questions = quesDao.findById(qid);
        if(users == null || questions == null) return "Error";
        else {
            UserAndQues userAndQues = new UserAndQues();
            userAndQues.setQid(qid);
            userAndQues.setUid(uid);
            userAndQues.setUsers(users);
            userAndQues.setQuestions(questions);
            userAndQuesDao.addFollow(userAndQues);
            return "OK";
        }
    }
}
