package com.backend.seqaq.dao.impl;

import com.backend.seqaq.dao.FollowersDao;
import com.backend.seqaq.entity.Followers;
import com.backend.seqaq.repository.FollowersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FollowersDaoImpl implements FollowersDao {
  @Autowired private FollowersRepository followersRepository;

  public List<Followers> findAllFollowedByUid(Long uid) {
    return followersRepository.findAllByUid1(uid);
  }

  public List<Followers> findAllFollowerByUid(Long uid) {
    return followersRepository.findAllByUid2(uid);
  }

  public void addFollow(Followers followers) {
    followersRepository.save(followers);
  }

  public void delFollow(Long uid1, Long uid2) {
    followersRepository.deleteAllByUid1AndUid2(uid1, uid2);
  }

  public List<Long> recommend(Long uid) {
    return followersRepository.recommend(uid);
  }

  public List<Long> recommendQues(Long uid) {
    return followersRepository.recommendQues(uid);
  }

  @Override
  public boolean checkFollowed(Long uid1, Long uid2) {
    return followersRepository.existsByUid1AndUid2(uid1, uid2);
  }
}
