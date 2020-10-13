package com.backend.seqaq.dao;


import com.backend.seqaq.entity.UserAndQues;

import java.util.List;

public interface UserAndQuesDao {
    List<UserAndQues> findAllQuesByUid(Long uid);
    List<UserAndQues> findAllUsersByQid(Long qid);
    void delFollow(Long uid,Long qid);
    void addFollow(UserAndQues userAndQues);
}
