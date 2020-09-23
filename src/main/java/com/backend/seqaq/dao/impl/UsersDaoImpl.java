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

    public Users FindById(Long id){
        return usersRepository.findById(id).orElse(null);
    }
    public Users FindByAccount(String account){
        return usersRepository.findByAccount(account);
    }
    public String Register(Users u){
        if(FindByAccount(u.getAccount())!=null) return "Account Exists";
        else {
            usersRepository.save(u);
        }
        return "Success";
    }

}
