package com.backend.seqaq.service;

import org.springframework.stereotype.Service;

public interface AnswersService {
    String addAnswers(Long uid,Long qid,String text);
    String  editAnswers(Long aid,String text);
    String banAnswers(Long aid);
    String unbanAnswers(Long aid);
    String deleteAnswers(Long aid);
}
