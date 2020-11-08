package com.backend.seqaq.dao.impl;

import com.backend.seqaq.dao.RepliesDao;
import com.backend.seqaq.entity.Replies;
import com.backend.seqaq.repository.RepliesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RepliesDaoImpl implements RepliesDao {
    @Autowired
    private RepliesRepository repliesRepository;

    public void reply(Replies replies)
    {
        repliesRepository.save(replies);
    }
    public Replies findReply(Long rid){
        return repliesRepository.findById(rid).orElse(null);
    }
    public List<Replies> findByUid(Long uid){
        return repliesRepository.findAllByUidAndDtype(uid,0);
    }
    public List<Replies> findByAid(Long aid){
        return repliesRepository.findAllByDidAndDtype(aid,0);
    }
    public List<Replies> findByRid(Long rid) {
        return repliesRepository.findAllByDidAndDtype(rid,1);
    }
}
