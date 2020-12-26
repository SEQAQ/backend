package com.backend.seqaq.dao.impl;

import com.backend.seqaq.dao.AnswersDao;
import com.backend.seqaq.entity.AnswerDetail;
import com.backend.seqaq.entity.Answers;
import com.backend.seqaq.repository.AnswerDetailRepository;
import com.backend.seqaq.repository.AnswersRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AnswersDaoImpl implements AnswersDao {
  private final AnswersRepository answersRepository;
  private final AnswerDetailRepository answerDetailRepository;

  public AnswersDaoImpl(
      AnswersRepository answersRepository, AnswerDetailRepository answerDetailRepository) {
    this.answersRepository = answersRepository;
    this.answerDetailRepository = answerDetailRepository;
  }

  @Transactional
  public Long addOrChangeAnswer(Answers answers) {
    Answers ans = answersRepository.save(answers);
    AnswerDetail detail = answers.getDetail();
    detail.setAid(ans.getAid());
    return answerDetailRepository.save(detail).getAid();
  }

  public List<Answers> findAllByQid(Long qid) {
    List<Answers> list = answersRepository.findAllByQid(qid);
    list.forEach(this::attachDetail);
    return list;
  }

  public List<Answers> findAllByUid(Long uid) {
    List<Answers> list = answersRepository.findAllByUid(uid);
    list.forEach(this::attachDetail);
    return list;
  }

  public Answers findById(Long aid) {
    return attachDetail(answersRepository.findById(aid).orElse(null));
  }

  private Answers attachDetail(Answers answer) {
    if (answer == null) return null;
    AnswerDetail detail = answerDetailRepository.findByAid(answer.getAid()).orElse(null);
    answer.setDetail(detail);
    return answer;
  }
}
