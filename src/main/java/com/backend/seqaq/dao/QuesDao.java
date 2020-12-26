package com.backend.seqaq.dao;

import com.backend.seqaq.entity.Questions;

import java.util.List;

public interface QuesDao {
  List<Questions> findByUid(Long uid);

  Long save(Questions questions);

  Questions findById(Long id);

  List<Questions> findAllByTitleContaining(String text);

  List<Questions> findAllByTagContaining(String text);

  List<Questions> findAllByDetailContaining(String text);
}
