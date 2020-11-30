package com.backend.seqaq.service;

import com.backend.seqaq.entity.ConfirmationToken;
import com.backend.seqaq.entity.Users;

public interface ConfirmationTokenService {
  ConfirmationToken findByToken(String token);

  void saveNewToken(Users user, String tokenString);
}
