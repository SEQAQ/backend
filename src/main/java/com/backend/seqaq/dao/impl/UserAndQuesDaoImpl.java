package com.backend.seqaq.dao.impl;

import com.backend.seqaq.dao.UserAndQuesDao;
import com.backend.seqaq.entity.UserAndQues;
import com.backend.seqaq.repository.UserAndQuesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserAndQuesDaoImpl implements UserAndQuesDao {
  @Autowired private UserAndQuesRepository userAndQuesRepository;

  public List<UserAndQues> findAllQuesByUid(Long uid) {
    return userAndQuesRepository.findAllByUid(uid);
  }

  public List<UserAndQues> findAllUsersByQid(Long qid) {
    return userAndQuesRepository.findAllByQid(qid);
  }

  public void delFollow(Long uid, Long qid) {
    userAndQuesRepository.deleteByUidAndQid(uid, qid);
  }

  public void addFollow(UserAndQues userAndQues) {
    userAndQuesRepository.save(userAndQues);
  }

  public boolean existsByUidAndQid(Long uid,Long qid) {
    return userAndQuesRepository.existsByUidAndQid(uid,qid);
  }
}
