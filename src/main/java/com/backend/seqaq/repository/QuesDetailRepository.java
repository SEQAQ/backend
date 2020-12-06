package com.backend.seqaq.repository;

import com.backend.seqaq.entity.QuestionDetail;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface QuesDetailRepository extends MongoRepository<QuestionDetail, ObjectId> {
  Optional<QuestionDetail> findByQid(Long qid);

  List<QuestionDetail> findAllByDetailContaining(String query);

  @Query(value = "{'detail': /.*?0.*/}", fields = "{'qid': 1, '_id': 0}")
  List<QuestionDetail> findIdByDetailContaining(String query);
}
