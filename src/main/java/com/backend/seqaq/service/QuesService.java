package com.backend.seqaq.service;

import com.alibaba.fastjson.JSONObject;
import com.backend.seqaq.entity.Questions;

import java.util.List;

public interface QuesService {
  List<Questions> findByUid(Long uid);

  String createQues(String title, String tag, Long uid);

  Questions createQuestion(JSONObject json);

  Questions findById(Long qid);

  void editQues(Long qid, String text);

  void banQues(Long qid);

  void unbanQues(Long qid);

  void delQues(Long qid);
}
