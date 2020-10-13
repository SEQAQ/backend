package com.backend.seqaq.repository;

import com.backend.seqaq.entity.Answers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnswersRepository extends JpaRepository<Answers,Long> {
    List<Answers> findAllByQid(Long qid);
    List<Answers> findAllByUid(Long uid);
}
