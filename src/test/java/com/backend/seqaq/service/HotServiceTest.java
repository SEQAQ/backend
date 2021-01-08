package com.backend.seqaq.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class HotServiceTest {

  @Autowired private HotService hotService;

  @Test
  void add() {
    hotService.add(1L, 1L);
  }

  @Test
  void getTop10() {
    System.out.println(hotService.getTop10(0));
    System.out.println(hotService.getTop10(1));
    System.out.println(hotService.getTop10(2));
  }
}
