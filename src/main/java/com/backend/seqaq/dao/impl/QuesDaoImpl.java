package com.backend.seqaq.dao.impl;


import com.backend.seqaq.dao.QuesDao;
import com.backend.seqaq.entity.Questions;
import com.backend.seqaq.entity.Users;
import com.backend.seqaq.repository.QuesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class QuesDaoImpl implements QuesDao {
    @Autowired
    private QuesRepository quesRepository;
    public List<Questions> findByUid(Long uid){
        return quesRepository.findAllByUid(uid);
    }
    public void save(Questions questions){
        System.out.println("ok");
        quesRepository.save(questions);
    }
    public Questions findById(Long id){
        return quesRepository.findById(id).orElse(null);
    }
}
