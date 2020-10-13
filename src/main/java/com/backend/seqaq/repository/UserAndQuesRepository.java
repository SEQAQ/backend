package com.backend.seqaq.repository;

import com.backend.seqaq.entity.UserAndQues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserAndQuesRepository extends JpaRepository<UserAndQues,Long> {
    List<UserAndQues> findAllByUid(Long uid);
    List<UserAndQues> findAllByQid(Long qid);

    @Transactional
    void deleteByUidAndQid(Long uid,Long qid);
}
