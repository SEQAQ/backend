package com.backend.seqaq.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.backend.seqaq.dao.QuesDao;
import com.backend.seqaq.dao.UsersDao;
import com.backend.seqaq.entity.QuestionDetail;
import com.backend.seqaq.entity.Questions;
import com.backend.seqaq.entity.Users;
import com.backend.seqaq.service.QuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class QuesServiceImpl implements QuesService {
    @Autowired
    private QuesDao quesDao;
    @Autowired
    private UsersDao usersDao;

    public List<Questions> findByUid(Long uid) {
        Users users = usersDao.findById(uid);
        if (users == null) return null;
        else return quesDao.findByUid(uid);
    }

    public Questions findById(Long qid) {
        return quesDao.findById(qid);
    }

    public String createQues(String title, String tag, Long uid) {
        Questions questions = new Questions();
        Users u = usersDao.findById(uid);
        if (u == null) return "Error";
        questions.setUsers(u);
        questions.setFollower(0L);
        questions.setStatus(1);
        questions.setTag(tag);
        questions.setTitle(title);
        questions.setUid(uid);
        Timestamp d = new Timestamp(System.currentTimeMillis());
        questions.setCtime(d);
        questions.setMtime(d);
        System.out.println(questions);
        quesDao.save(questions);
        return "OK";
    }

    @Override
    public Questions createQuestion(JSONObject json) {
        Questions question = new Questions();
        question.setTitle(json.getString("title"));
        question.setUid(json.getLong("uid"));
        QuestionDetail detail = new QuestionDetail(json.getString("detail"));
        question.setDetail(detail);
        quesDao.save(question);
        return null;
    }

    public void editQues(Long qid, String text) {
        Questions questions = quesDao.findById(qid);
        Timestamp d = new Timestamp(System.currentTimeMillis());
        questions.setMtime(d);
        quesDao.save(questions);
    }

    public void banQues(Long qid) {
        Questions questions = quesDao.findById(qid);
        questions.setStatus(0);
        quesDao.save(questions);
    }

    public void unbanQues(Long qid) {
        Questions questions = quesDao.findById(qid);
        questions.setStatus(1);
        quesDao.save(questions);
    }

    public void delQues(Long qid) {
        Questions questions = quesDao.findById(qid);
        questions.setStatus(-1);
        quesDao.save(questions);
    }

}
