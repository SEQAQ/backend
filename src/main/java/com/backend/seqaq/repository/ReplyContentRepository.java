package com.backend.seqaq.repository;

import com.backend.seqaq.entity.ReplyContent;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ReplyContentRepository extends MongoRepository<ReplyContent,
        ObjectId> {
    Optional<ReplyContent> findByRid(Long rid);
}
