package com.backend.seqaq.service;

import com.alibaba.fastjson.JSONObject;
import com.backend.seqaq.entity.Questions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface QuesService {
  List<Questions> findByUid(Long uid);

  String createQuestion(JSONObject json);

  Questions findById(Long qid);

  String editQues(Long qid, String title, String detail);

  String editQues(Long qid, String title);

  String banQues(Long qid);

  String unbanQues(Long qid);

  String delQues(Long qid);

  String close(Long qid);

  Page<Questions> findAll(Pageable pageable);

  List<Questions> findAll();
}
