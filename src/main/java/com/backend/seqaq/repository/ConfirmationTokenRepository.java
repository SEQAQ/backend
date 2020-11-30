package com.backend.seqaq.repository;

import com.backend.seqaq.entity.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {
  ConfirmationToken findByToken(String token);
}
