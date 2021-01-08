package com.backend.seqaq.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.backend.seqaq.dao.QuesDao;
import com.backend.seqaq.dao.UsersDao;
import com.backend.seqaq.entity.QuestionDetail;
import com.backend.seqaq.entity.Questions;
import com.backend.seqaq.entity.Users;
import com.backend.seqaq.event.OnNewQuestionEvent;
import com.backend.seqaq.service.QuesService;
import com.backend.seqaq.tools.examine.Examine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class QuesServiceImpl implements QuesService {
  @Autowired private QuesDao quesDao;
  @Autowired private UsersDao usersDao;
  private Examine examine = new Examine();
  @Autowired private ApplicationEventPublisher eventPublisher;

  public int checklevel(int exp) {
    if (exp < 50) return 1;
    else if (exp < 150) return 2;
    else if (exp < 300) return 3;
    else if (exp < 600) return 4;
    else if (exp < 1000) return 5;
    else return 6;
  }

  public List<Questions> findByUid(Long uid) {
    Users users = usersDao.findById(uid);
    if (users == null) return null;
    else return quesDao.findByUid(uid);
  }

  public Questions findById(Long qid) {
    return quesDao.findById(qid);
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
    question.setTag(json.getString("tag"));
    question.setFollower(0L);
    question.setStatus(1);
    Timestamp d = new Timestamp(System.currentTimeMillis());
    question.setCtime(d);
    question.setMtime(d);
    org.json.JSONObject object = examine.forText(detail.getDetail());
    org.json.JSONObject object2 = examine.forText(question.getTitle());
    org.json.JSONObject object3 = examine.forText(question.getTag());
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
    if (object2.getInt("conclusionType") != 1) {
      String words =
          object2
              .getJSONArray("data")
              .getJSONObject(0)
              .getJSONArray("hits")
              .getJSONObject(0)
              .getJSONArray("words")
              .toString();
      return "问题标题存在敏感词汇: " + words + " 等";
    }
    if (question.getTag().length() != 0 && object3.getInt("conclusionType") != 1) {
      String words =
          object3
              .getJSONArray("data")
              .getJSONObject(0)
              .getJSONArray("hits")
              .getJSONObject(0)
              .getJSONArray("words")
              .toString();
      return "问题标签存在敏感词汇: " + words + " 等";
    }
    question.setDetail(detail);
    int exp = u.getExp();
    int level = 1;
    exp += 10;
    if (exp > 1000) {
      exp = 1000;
      level = 6;
    } else level = checklevel(exp);
    u.setExp(exp);
    u.setLevel(level);
    usersDao.saveUser(u);
    String result = quesDao.save(question).toString();
    eventPublisher.publishEvent(new OnNewQuestionEvent(question));
    return result;
  }

  public String editQues(Long qid, String title, String detailString) {
    Questions questions = quesDao.findById(qid);
    if (questions == null) return "Error";
    Timestamp d = new Timestamp(System.currentTimeMillis());
    questions.setMtime(d);
    org.json.JSONObject object = examine.forText(title);
    if (object.getInt("conclusionType") != 1) {
      String words =
          object
              .getJSONArray("data")
              .getJSONObject(0)
              .getJSONArray("hits")
              .getJSONObject(0)
              .getJSONArray("words")
              .toString();
      return "问题标题存在敏感词汇: " + words + " 等";
    }


    questions.setTitle(title);
    object = examine.forText(detailString);
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
    QuestionDetail detail = questions.getDetail();
    detail.setDetail(detailString);
    questions.setDetail(detail);
    return quesDao.save(questions).toString();
  }


  public String editQues(Long qid, String title) {
    Questions questions = quesDao.findById(qid);
    if (questions == null) return "Error";
    Timestamp d = new Timestamp(System.currentTimeMillis());
    questions.setMtime(d);
    org.json.JSONObject object = examine.forText(title);
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
    questions.setTitle(title);
    return quesDao.save(questions).toString();
  }


  public String banQues(Long qid) {
    Questions questions = quesDao.findById(qid);
    if (questions == null) return "Error";
    questions.setStatus(0);
    quesDao.save(questions);
    return "OK";
  }
  public String close(Long qid) {
    Questions questions = quesDao.findById(qid);
    if (questions == null) return "Error";
    questions.setStatus(2);
    quesDao.save(questions);
    return "OK";
  }


  public String unbanQues(Long qid) {
    Questions questions = quesDao.findById(qid);
    if (questions == null) return "Error";
    questions.setStatus(1);
    quesDao.save(questions);
    return "OK";
  }
  public Page<Questions> findAll(Pageable pageable) {
    return quesDao.findAll(pageable);
  }
  public List<Questions> findAll() {
    return quesDao.findAll();
  }
  public String delQues(Long qid) {
    Questions questions = quesDao.findById(qid);
    if (questions == null) return "Error";
    questions.setStatus(-1);
    quesDao.save(questions);
    return "OK";
  }
}
