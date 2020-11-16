package com.backend.seqaq.repository;

import com.backend.seqaq.entity.Replies;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepliesRepository extends JpaRepository<Replies, Long> {
  List<Replies> findAllByUidAndDtype(Long uid, Integer dtype);

  List<Replies> findAllByDidAndDtype(Long did, Integer dtype);
}
