package com.backend.seqaq.repository;

import com.backend.seqaq.entity.AnswerDetail;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AnswerDetailRepository extends MongoRepository<AnswerDetail,
        ObjectId> {
    Optional<AnswerDetail> findByAid(Long aid);
}
