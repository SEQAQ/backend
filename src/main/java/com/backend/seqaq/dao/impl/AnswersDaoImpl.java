package com.backend.seqaq.dao.impl;

import com.backend.seqaq.dao.AnswersDao;
import com.backend.seqaq.entity.AnswerDetail;
import com.backend.seqaq.entity.Answers;
import com.backend.seqaq.repository.AnswerDetailRepository;
import com.backend.seqaq.repository.AnswersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AnswersDaoImpl implements AnswersDao {
    private final AnswersRepository answersRepository;
    private final AnswerDetailRepository answerDetailRepository;

    public AnswersDaoImpl(AnswersRepository answersRepository, AnswerDetailRepository answerDetailRepository) {
        this.answersRepository = answersRepository;
        this.answerDetailRepository = answerDetailRepository;
    }

    @Transactional
    public void addOrChangeAnswer(Answers answers) {
        answersRepository.save(answers);
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
        AnswerDetail detail =
                answerDetailRepository.findByAid(answer.getAid()).orElse(null);
        answer.setDetail(detail);
        return answer;
    }
}
