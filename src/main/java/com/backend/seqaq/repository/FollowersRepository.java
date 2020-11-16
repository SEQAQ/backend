package com.backend.seqaq.repository;

import com.backend.seqaq.entity.Followers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FollowersRepository extends JpaRepository<Followers, Long> {
  List<Followers> findAllByUid1(Long uid);

  List<Followers> findAllByUid2(Long uid);

  @Transactional
  void deleteAllByUid1AndUid2(Long uid1, Long uid2);
}
