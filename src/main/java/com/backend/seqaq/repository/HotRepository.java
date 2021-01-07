package com.backend.seqaq.repository;

import com.backend.seqaq.entity.Hot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface HotRepository extends JpaRepository<Hot, Long> {
  @Query(
      value =
          "SELECT qid\n"
              + "FROM\n"
              + "(select qid,COUNT(*) as sum\n"
              + "FROM hot\n"
              + "WHERE created_date>=?1 AND created_date <= ?2 \n"
              + "GROUP BY qid\n"
              + "ORDER BY sum DESC LIMIT 10) as w",
      nativeQuery = true)
  List<Long> getTop10(Date d1, Date d2);

  @Query(
      value =
          "select uid\n"
              + "from\n"
              + "(select uid,count(*) as num\n"
              + "from\n"
              + "(select qid as q0,uid as u0\n"
              + "from hot\n"
              + "where uid = 2) as w\n"
              + "join hot on w.q0 = hot.qid and w.u0 != hot.uid\n"
              + "group by uid\n"
              + "order by num desc limit 5) as y",
      nativeQuery = true)
  List<Long> recommend(Long uid);
}
