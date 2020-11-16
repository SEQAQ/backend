package com.backend.seqaq.repository;

import com.backend.seqaq.entity.UserDetail;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserDetailRepository extends MongoRepository<UserDetail, ObjectId> {
  Optional<UserDetail> findByUid(Long uid);
}
