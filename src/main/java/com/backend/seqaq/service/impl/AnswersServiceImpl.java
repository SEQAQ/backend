package com.backend.seqaq.service.impl;

import com.backend.seqaq.dao.AnswersDao;
import com.backend.seqaq.dao.QuesDao;
import com.backend.seqaq.dao.UsersDao;
import com.backend.seqaq.entity.AnswerDetail;
import com.backend.seqaq.entity.Answers;
import com.backend.seqaq.entity.Questions;
import com.backend.seqaq.entity.Users;
import com.backend.seqaq.service.AnswersService;
import com.backend.seqaq.tools.examine.Examine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service
public class AnswersServiceImpl implements AnswersService {

  @Autowired private AnswersDao answersDao;
  @Autowired private UsersDao usersDao;
  @Autowired private QuesDao quesDao;
  private Examine examine = new Examine();

  public Answers findAnswersById(Long aid) {
    return answersDao.findById(aid);
  }

  public List<Answers> findAnswersByUid(Long uid) {
    return answersDao.findAllByUid(uid);
  }

  @Override
  public List<Answers> findAnswersByQid(Long qid) {
    return answersDao.findAllByQid(qid);
  }

  public String addAnswers(Long uid, Long qid, String text) {
    Users users = usersDao.findById(uid);
    Questions questions = quesDao.findById(qid);
    if (users == null || questions == null) return "Error";
    else {
      Answers answers = new Answers();
      answers.setDislike(0L);
      answers.setLike(0L);
      answers.setQid(qid);
      answers.setQuestions(questions);
      answers.setStatus(1);
      answers.setUid(uid);
      answers.setUsers(users);
      answers.setQuestions(questions);
      Timestamp d = new Timestamp(System.currentTimeMillis());
      answers.setCtime(d);
      answers.setMtime(d);
      org.json.JSONObject object = examine.forText(text);
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
      AnswerDetail detail = new AnswerDetail();
      detail.setMdText(text);
      answers.setDetail(detail);
      answersDao.addOrChangeAnswer(answers);
      return "OK";
    }
  }

  @Transactional
  public String editAnswers(Long aid, String text) {
    Answers answers = answersDao.findById(aid);
    if (answers == null) return "Error";
    else {
      Timestamp d = new Timestamp(System.currentTimeMillis());
      answers.setMtime(d);
      return "OK";
    }
  }

  @Transactional
  public String banAnswers(Long aid) {
    Answers answers = answersDao.findById(aid);
    if (answers == null) return "Error";
    else {
      answers.setStatus(0);
      answersDao.addOrChangeAnswer(answers);
      return "OK";
    }
  }

  @Transactional
  public String unbanAnswers(Long aid) {
    Answers answers = answersDao.findById(aid);
    if (answers == null) return "Error";
    else {
      answers.setStatus(1);
      answersDao.addOrChangeAnswer(answers);
      return "OK";
    }
  }

  @Transactional
  public String likeAnswers(Long aid) {
    Answers answers = answersDao.findById(aid);
    if (answers == null) return "Error";
    else {
      Long like = answers.getLike();
      answers.setLike(like + 1);
      answersDao.addOrChangeAnswer(answers);
      return "OK";
    }
  }

  @Transactional
  public String dislikeAnswers(Long aid) {
    Answers answers = answersDao.findById(aid);
    if (answers == null) return "Error";
    else {
      Long dislike = answers.getDislike();
      answers.setDislike(dislike + 1);
      answersDao.addOrChangeAnswer(answers);
      return "OK";
    }
  }

  @Transactional
  public String deleteAnswers(Long aid) {
    Answers answers = answersDao.findById(aid);
    if (answers == null) return "Error";
    else {
      answers.setStatus(-1);
      answersDao.addOrChangeAnswer(answers);
      return "OK";
    }
  }
}
