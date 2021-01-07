package com.backend.seqaq.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class HotServiceTest {
  @Autowired HotService hotService;


  @Test
  void help() {
    Date d = new java.util.Date();
    java.sql.Date date = new java.sql.Date(d.getTime()); // 会丢失时分秒

    Calendar calendar = new GregorianCalendar();
    calendar.setTime(date);
    calendar.add(calendar.DATE, -7);
    // calendar的time转成java.util.Date格式日期
    java.util.Date utilDate = (java.util.Date) calendar.getTime();
    calendar.add(calendar.DATE, 6);
    utilDate = (java.util.Date) calendar.getTime();
    // java.util.Date日期转换成转成java.sql.Date格式
    java.sql.Date newDate = new java.sql.Date(utilDate.getTime());
    System.out.println(newDate);
  }

  @Test
  void getTop10() {
    System.out.println(hotService.getTop10(0));
    System.out.println(hotService.getTop10(1));
    System.out.println(hotService.getTop10(2));
  }
}
