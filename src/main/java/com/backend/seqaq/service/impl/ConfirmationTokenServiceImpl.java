package com.backend.seqaq.service.impl;

import com.backend.seqaq.dao.ConfirmationTokenDao;
import com.backend.seqaq.entity.ConfirmationToken;
import com.backend.seqaq.entity.Users;
import com.backend.seqaq.service.ConfirmationTokenService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {
    private final ConfirmationTokenDao confirmationTokenDao;

    public ConfirmationTokenServiceImpl(ConfirmationTokenDao confirmationTokenDao) {
        this.confirmationTokenDao = confirmationTokenDao;
    }

    @Override
    public ConfirmationToken findByToken(String token) {
        return confirmationTokenDao.findByToken(token);
    }

    @Override
    public void saveNewToken(Users user, String tokenString) {
        ConfirmationToken confirmationToken = new ConfirmationToken();
        confirmationToken.setUser(user);
        confirmationToken.setToken(tokenString);
        confirmationToken.setCreatedDate(new Date());
        confirmationTokenDao.saveToken(confirmationToken);
    }
}
