package com.backend.seqaq.service;

import com.backend.seqaq.entity.Answers;

import java.util.List;

public interface AnswersService {

  String addAnswers(Long uid, Long qid, String text);

  String editAnswers(Long aid, String text);

  String banAnswers(Long aid);

  String unbanAnswers(Long aid);

  String deleteAnswers(Long aid);

  Answers findAnswersById(Long aid);

  String likeAnswers(Long aid,Long uid);

  String unlikeAnswers(Long aid,Long uid);

  String dislikeAnswers(Long aid);

  String undislikeAnswers(Long aid);

  List<Answers> findAnswersByUid(Long uid);

  List<Answers> findAnswersByQid(Long qid);
}
