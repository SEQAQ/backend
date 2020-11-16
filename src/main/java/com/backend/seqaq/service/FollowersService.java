package com.backend.seqaq.service;

import com.backend.seqaq.entity.Followers;

import java.util.List;

public interface FollowersService {
  List<Followers> findAllFollowedByUid(Long uid);

  List<Followers> findAllFollowerByUid(Long uid);

  String addFollow(Long uid1, Long uid2);

  String delFollow(Long uid1, Long uid2);
}
