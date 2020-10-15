package com.backend.seqaq.dao;

import com.backend.seqaq.entity.Questions;
import com.backend.seqaq.entity.Users;

import java.util.List;

public interface QuesDao {
    List<Questions> findByUid(Long uid);
    void save(Questions questions);
    Questions findById(Long id);
}
