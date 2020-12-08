package com.backend.seqaq.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.backend.seqaq.dao.QuesDao;
import com.backend.seqaq.dao.UsersDao;
import com.backend.seqaq.entity.QuestionDetail;
import com.backend.seqaq.entity.Questions;
import com.backend.seqaq.entity.Users;
import com.backend.seqaq.service.QuesService;
import com.backend.seqaq.tools.examine.Examine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class QuesServiceImpl implements QuesService {
  @Autowired private QuesDao quesDao;
  @Autowired private UsersDao usersDao;
  private Examine examine = new Examine();

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
  public String createQuestion(JSONObject json) {
    Questions question = new Questions();
    Users u = usersDao.findById(json.getLong("uid"));
    if (u == null) return "Error";
    question.setTitle(json.getString("title"));
    question.setUid(json.getLong("uid"));
    QuestionDetail detail = new QuestionDetail(json.getString("detail"));
    question.setUsers(u);
    question.setFollower(0L);
    question.setStatus(1);
    Timestamp d = new Timestamp(System.currentTimeMillis());
    question.setCtime(d);
    question.setMtime(d);
    org.json.JSONObject object = examine.forText(detail.getDetail());
    if (object.getInt("conclusionType") != 1) {
      String words =
          object
              .getJSONArray("data")
              .getJSONObject(0)
              .getJSONArray("hits")
              .getJSONObject(0)
              .getJSONArray("words")
              .toString();
      return "问题内容存在敏感词汇: " + words + " 等";
    }
    question.setDetail(detail);
    quesDao.save(question);
    return "OK";
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
