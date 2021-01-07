package com.backend.seqaq.repository;

import com.backend.seqaq.entity.Followers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FollowersRepository extends JpaRepository<Followers, Long> {
  List<Followers> findAllByUid1(Long uid);

  List<Followers> findAllByUid2(Long uid);

  @Transactional
  void deleteAllByUid1AndUid2(Long uid1, Long uid2);

  boolean existsByUid1AndUid2(Long uid1, Long uid2);

  @Query(
      value =
          "SELECT uid2\n"
              + "FROM\n"
              + "(SELECT uid2,COUNT(*) as num\n"
              + "from(\n"
              + "SELECT *\n"
              + "FROM (SELECT uid1 as uid0,uid2 as uid\n"
              + "FROM followers\n"
              + "WHERE uid1 = 1) as w \n"
              + "JOIN followers\n"
              + "ON w.uid = followers.uid1 and w.uid0 != followers.uid2) as u\n"
              + "GROUP BY uid2\n"
              + "ORDER BY num desc LIMIT 5) as x",
      nativeQuery = true)
  List<Long> recommend(Long uid);

  @Query(
      value =
          "select qid\n"
              + "from\n"
              + "(select qid,count(*) as num\n"
              + "from\n"
              + "(select uid1,uid2\n"
              + "from followers\n"
              + "where uid1 = 1) as w\n"
              + "join userandques on w.uid2 = userandques.uid\n"
              + "group by qid\n"
              + "order by num desc limit 5) as y",
      nativeQuery = true)
  List<Long> recommendQues(Long uid);
}
