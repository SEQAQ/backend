package com.backend.seqaq.dao;

import com.backend.seqaq.entity.Questions;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface QuesDao {
  List<Questions> findByUid(Long uid);

  Long save(Questions questions);

  Questions findById(Long id);

  List<Questions> findAllByTitleContaining(String text);

  List<Questions> findAllByTagContaining(String text);

  List<Questions> findAllByDetailContaining(String text);

  Page<Questions> findAll(Pageable pageable);
}
