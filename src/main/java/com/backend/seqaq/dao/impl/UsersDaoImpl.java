package com.backend.seqaq.dao.impl;

import com.backend.seqaq.dao.UsersDao;
import com.backend.seqaq.entity.Users;
import com.backend.seqaq.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UsersDaoImpl implements UsersDao {
    @Autowired
    private UsersRepository usersRepository;

    public Users findById(Long id){
        return usersRepository.findById(id).orElse(null);
    }
    public Users findByAccount(String account){
        return usersRepository.findByAccount(account);
    }
    public String register(Users u){
        if(findByAccount(u.getAccount())!=null) return "Account Exists";
        else {
            usersRepository.save(u);
        }
        return "OK";
    }

}
