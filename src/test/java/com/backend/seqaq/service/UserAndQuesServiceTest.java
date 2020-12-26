package com.backend.seqaq.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserAndQuesServiceTest {

  @Autowired private UserAndQuesService userAndQuesService;
  @Autowired private FollowersService followersService;

  @Test
  void UAQ() {
    userAndQuesService.addFollow(1L, 1L);
    System.out.println(userAndQuesService.findAllUsersByQid(1L));
    System.out.println(userAndQuesService.findAllQuesByUid(1L));
    assertEquals(true, userAndQuesService.existsByUidAndQid(1L, 1L));
    userAndQuesService.delFollow(1L, 1L);
    assertEquals(false, userAndQuesService.existsByUidAndQid(1L, 1L));
  }

  @Test
  void Follow() {
    followersService.addFollow(1L, 2L);
    followersService.addFollow(2L, 1L);
    System.out.println(followersService.findAllFollowedByUid(2L));
    System.out.println(followersService.findAllFollowerByUid(2L));
    assertEquals(true, followersService.checkFollowed(1L, 2L));
    followersService.delFollow(1L, 2L);
    followersService.delFollow(2L, 1L);
    assertEquals(false, followersService.checkFollowed(1L, 2L));
  }
}
