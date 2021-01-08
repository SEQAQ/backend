package com.backend.seqaq.service;

import com.backend.seqaq.entity.UserAndQues;

import java.util.List;

public interface UserAndQuesService {
  List<UserAndQues> findAllQuesByUid(Long uid);

  List<UserAndQues> findAllUsersByQid(Long qid);

  String delFollow(Long uid, Long qid);

  String addFollow(Long uid, Long qid);

  boolean existsByUidAndQid(Long uid, Long qid);

  int checklevel(int exp);
}
