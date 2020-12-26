package com.backend.seqaq.service;

import com.alibaba.fastjson.JSONObject;
import com.backend.seqaq.entity.Questions;

import java.util.List;

public interface QuesService {
  List<Questions> findByUid(Long uid);

  String createQuestion(JSONObject json);

  Questions findById(Long qid);

  String editQues(Long qid, String text);

  String banQues(Long qid);

  String unbanQues(Long qid);

  String delQues(Long qid);
}
