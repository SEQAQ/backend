package com.backend.seqaq.repository;

import com.backend.seqaq.entity.Like_record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Like_recordRepository extends JpaRepository<Like_record,Long> {
    boolean existsByAidAndUid(Long aid,Long uid);
    void deleteByAidAndUid(Long aid,Long uid);
}
