package com.backend.seqaq.dao.impl;

import com.backend.seqaq.dao.AnswersDao;
import com.backend.seqaq.entity.Answers;
import com.backend.seqaq.repository.AnswersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AnswersDaoImpl implements AnswersDao {
    @Autowired
    private AnswersRepository answersRepository;

    @Transactional
    public void addOrChangeAnswer(Answers answers){
        answersRepository.save(answers);
    }

    public List<Answers> findAllByQid(Long qid) {
        return answersRepository.findAllByQid(qid);
    }
    public List<Answers> findAllByUid(Long uid) {
        return answersRepository.findAllByUid(uid);
    }
    public Answers findById(Long aid){
        return answersRepository.findById(aid).orElse(null);
    }
}
