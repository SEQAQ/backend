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
    public Users findById(Long id){
        return usersDao.findById(id);
    }
    public Users findByAccount(String account){
        return usersDao.findByAccount(account);
    }
    public String register(Users u){
        return usersDao.register(u);
    }
    public String banUser(Long uid)
    {
        Users users = usersDao.findById(uid);
        if(users == null) return "Error";
        else {
            users.setStatus(0);
            return "OK";
        }
    }
    public String unbanUser(Long uid)
    {
        Users users = usersDao.findById(uid);
        if(users == null) return "Error";
        else {
            users.setStatus(1);
            return "OK";
        }
    }
}
