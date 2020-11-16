package com.backend.seqaq.dao;

import com.backend.seqaq.entity.Followers;

import java.util.List;

public interface FollowersDao {
  List<Followers> findAllFollowedByUid(Long uid);

  List<Followers> findAllFollowerByUid(Long uid);

  void delFollow(Long uid1, Long uid2);

  void addFollow(Followers followers);
}
