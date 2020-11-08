package com.backend.seqaq.service;


import com.backend.seqaq.entity.Answers;

public interface AnswersService {
    String addAnswers(Long uid,Long qid,String text);
    String  editAnswers(Long aid,String text);
    String banAnswers(Long aid);
    String unbanAnswers(Long aid);
    String deleteAnswers(Long aid);
    Answers findAnswersByid(Long aid);
    String likeAnswers(Long aid);
    String dislikeAnswers(Long aid);
}
