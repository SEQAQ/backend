package com.backend.seqaq.service.impl;

import com.backend.seqaq.dao.UsersDao;
import com.backend.seqaq.entity.Users;
import com.backend.seqaq.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UsersDao usersDao;
    public Users FindById(Long id){
        return usersDao.FindById(id);
    }
    public Users FindByAccount(String account){
        return usersDao.FindByAccount(account);
    }
    public String Register(Users u){
        return usersDao.Register(u);
    }

}
