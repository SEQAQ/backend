package com.backend.seqaq.service.impl;

import com.backend.seqaq.dao.AnswersDao;
import com.backend.seqaq.dao.QuesDao;
import com.backend.seqaq.dao.UsersDao;
import com.backend.seqaq.entity.*;
import com.backend.seqaq.event.OnNewAnswerEvent;
import com.backend.seqaq.repository.Like_recordRepository;
import com.backend.seqaq.service.AnswersService;
import com.backend.seqaq.tools.examine.Examine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service
public class AnswersServiceImpl implements AnswersService {

  @Autowired private AnswersDao answersDao;
  @Autowired private UsersDao usersDao;
  @Autowired private QuesDao quesDao;
  @Autowired private Like_recordRepository like_recordRepository;
  private Examine examine = new Examine();
  @Autowired private ApplicationEventPublisher eventPublisher;

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

  @Transactional
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
      String result = answersDao.addOrChangeAnswer(answers).toString();
      eventPublisher.publishEvent(new OnNewAnswerEvent(answers));
      return result;
    }
  }

  @Transactional
  public String editAnswers(Long aid, String text) {
    Answers answers = answersDao.findById(aid);
    if (answers == null) return "Error";
    else {
      Timestamp d = new Timestamp(System.currentTimeMillis());
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
      AnswerDetail detail = answers.getDetail();
      detail.setMdText(text);
      answers.setDetail(detail);
      answersDao.addOrChangeAnswer(answers);
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
  public String likeAnswers(Long aid,Long uid) {
    Answers answers = answersDao.findById(aid);
    if (answers == null) return "Error";
    else {
      Long like = answers.getLike();
      answers.setLike(like + 1);
      answersDao.addOrChangeAnswer(answers);
      Like_record like_record = new Like_record();
      like_record.setAid(aid);
      like_record.setUid(uid);
      like_recordRepository.save(like_record);
      return "OK";
    }
  }

  @Transactional
  public String unlikeAnswers(Long aid,Long uid) {
    Answers answers = answersDao.findById(aid);
    if (answers == null) return "Error";
    else {
      Long like = answers.getLike();
      answers.setLike(like - 1);
      answersDao.addOrChangeAnswer(answers);
      like_recordRepository.deleteByAidAndUid(aid,uid);
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
  public String undislikeAnswers(Long aid) {
    Answers answers = answersDao.findById(aid);
    if (answers == null) return "Error";
    else {
      Long dislike = answers.getDislike();
      answers.setDislike(dislike - 1);
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
