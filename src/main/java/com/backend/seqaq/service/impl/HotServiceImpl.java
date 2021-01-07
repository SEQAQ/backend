package com.backend.seqaq.service.impl;

import com.backend.seqaq.dao.QuesDao;
import com.backend.seqaq.entity.Hot;
import com.backend.seqaq.entity.Questions;
import com.backend.seqaq.repository.HotRepository;
import com.backend.seqaq.service.HotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;

@Service
public class HotServiceImpl implements HotService {
  @Autowired private HotRepository hotRepository;
  @Autowired private QuesDao quesDao;

  public void add(Long qid, Long uid) {
    Hot hot = new Hot();
    Timestamp d = new Timestamp(System.currentTimeMillis());
    hot.setCtime(d);
    hot.setQid(qid);
    hot.setUid(uid);
    hotRepository.save(hot);
  }

  public List<Questions> getTop10(int option) {
    Date d = new java.util.Date();
    java.sql.Date date = new java.sql.Date(d.getTime()); // 会丢失时分秒

    Calendar calendar = new GregorianCalendar();
    calendar.setTime(date);
    calendar.add(calendar.DATE, +1);
    java.util.Date utilDate = (java.util.Date) calendar.getTime();
    date = new java.sql.Date(utilDate.getTime());
    switch (option) {
      case 0:
        calendar.add(calendar.DATE, -1);
        break;
      case 1:
        calendar.add(calendar.DATE, -7);
        break;
      case 2:
        calendar.add(calendar.DATE, -30);
        break;
    }
    utilDate = (java.util.Date) calendar.getTime();
    java.sql.Date newDate = new java.sql.Date(utilDate.getTime());
    System.out.println(newDate);
    System.out.println(date);
    List<Long> list = hotRepository.getTop10(newDate, date);
    List<Questions> q = new ArrayList<Questions>();
    for (int i = 0; i < list.size(); ++i) {
      q.add(i, quesDao.findById(list.get(i)));
    }
    return q;
  }
}
