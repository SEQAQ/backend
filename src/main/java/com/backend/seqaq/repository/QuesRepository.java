package com.backend.seqaq.repository;

import com.backend.seqaq.entity.Questions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuesRepository extends JpaRepository<Questions, Long> {
  List<Questions> findAllByUid(Long uid);

  List<Questions> findAllByTitleContaining(String text);

  List<Questions> findAllByTagContaining(String text);

  List<Questions> findByQidIn(List<Long> integers);
}
