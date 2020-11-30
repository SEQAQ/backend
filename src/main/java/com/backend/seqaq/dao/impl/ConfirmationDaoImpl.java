package com.backend.seqaq.dao.impl;

import com.backend.seqaq.dao.ConfirmationTokenDao;
import com.backend.seqaq.entity.ConfirmationToken;
import com.backend.seqaq.repository.ConfirmationTokenRepository;
import org.springframework.stereotype.Repository;

@Repository
public class ConfirmationDaoImpl implements ConfirmationTokenDao {
    private final ConfirmationTokenRepository confirmationTokenRepository;

    public ConfirmationDaoImpl(ConfirmationTokenRepository confirmationTokenRepository) {
        this.confirmationTokenRepository = confirmationTokenRepository;
    }

    @Override
    public ConfirmationToken findByToken(String token) {
        return confirmationTokenRepository.findByToken(token);
    }

    @Override
    public ConfirmationToken saveToken(ConfirmationToken token) {
        return confirmationTokenRepository.save(token);
    }
}
