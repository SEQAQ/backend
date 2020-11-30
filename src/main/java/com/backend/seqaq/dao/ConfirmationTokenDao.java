package com.backend.seqaq.dao;

import com.backend.seqaq.entity.ConfirmationToken;

public interface ConfirmationTokenDao {
  ConfirmationToken findByToken(String token);

  ConfirmationToken saveToken(ConfirmationToken token);
}
