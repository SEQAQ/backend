package com.backend.seqaq.dao;

import com.backend.seqaq.entity.Answers;

import java.util.List;

public interface AnswersDao {
    void addOrChangeAnswer(Answers answers);
    Answers findById(Long aid);
    List<Answers> findAllByQid(Long qid);
    List<Answers> findAllByUid(Long uid);
}
