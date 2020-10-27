package com.backend.seqaq.repository;

import com.backend.seqaq.entity.QuestionDetail;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface QuesDetailRepository extends MongoRepository<QuestionDetail,
        ObjectId> {
    Optional<QuestionDetail> findByQid(Long qid);
}
