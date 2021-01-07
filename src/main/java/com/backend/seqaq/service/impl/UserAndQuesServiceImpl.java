package com.backend.seqaq.service.impl;

import com.backend.seqaq.dao.QuesDao;
import com.backend.seqaq.dao.UserAndQuesDao;
import com.backend.seqaq.dao.UsersDao;
import com.backend.seqaq.entity.Questions;
import com.backend.seqaq.entity.UserAndQues;
import com.backend.seqaq.entity.Users;
import com.backend.seqaq.service.UserAndQuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAndQuesServiceImpl implements UserAndQuesService {
  @Autowired private UsersDao usersDao;
  @Autowired private QuesDao quesDao;
  @Autowired private UserAndQuesDao userAndQuesDao;


  private int checklevel(int exp) {
    if(exp<50) return 1;
    else if(exp<150) return 2;
    else if(exp<300) return 3;
    else if(exp<600) return 4;
    else if(exp<1000) return 5;
    else return 6;
  }
  public List<UserAndQues> findAllQuesByUid(Long uid) {
    return userAndQuesDao.findAllQuesByUid(uid);
  }

  public List<UserAndQues> findAllUsersByQid(Long qid) {
    return userAndQuesDao.findAllUsersByQid(qid);
  }

  public boolean existsByUidAndQid(Long uid, Long qid) {
    return userAndQuesDao.existsByUidAndQid(uid, qid);
  }

  public String delFollow(Long uid, Long qid) {
    userAndQuesDao.delFollow(uid, qid);
    return "OK";
  }

  public String addFollow(Long uid, Long qid) {
    Users users = usersDao.findById(uid);
    Questions questions = quesDao.findById(qid);
    if (users == null || questions == null) return "Error";
    else {
      UserAndQues userAndQues = new UserAndQues();
      userAndQues.setQid(qid);
      userAndQues.setUid(uid);
      userAndQues.setUsers(users);
      userAndQues.setQuestions(questions);
      Users u = usersDao.findById(questions.getUid());
      int exp = u.getExp();
      int level = 1;
      exp+=5;
      if(exp>1000)
      {
        exp = 1000;
        level = 6;
      }
      else level = checklevel(exp);
      u.setExp(exp);
      u.setLevel(level);
      usersDao.saveUser(u);
      userAndQuesDao.addFollow(userAndQues);
      return "OK";
    }
  }
}
