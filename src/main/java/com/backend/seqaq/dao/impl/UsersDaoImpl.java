package com.backend.seqaq.dao.impl;

import com.backend.seqaq.dao.UsersDao;
import com.backend.seqaq.entity.UserDetail;
import com.backend.seqaq.entity.Users;
import com.backend.seqaq.repository.UserDetailRepository;
import com.backend.seqaq.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UsersDaoImpl implements UsersDao {
    private final UsersRepository usersRepository;
    private final UserDetailRepository userDetailRepository;

    public UsersDaoImpl(UsersRepository usersRepository, UserDetailRepository userDetailRepository) {
        this.usersRepository = usersRepository;
        this.userDetailRepository = userDetailRepository;
    }

    public Users findById(Long id) {
        return attachDetail(usersRepository.findById(id).orElse(null));
    }

    public Users findByAccount(String account) {
        return attachDetail(usersRepository.findByAccount(account));
    }

    public String register(Users u) {
        if (findByAccount(u.getAccount()) != null) return "Account Exists";
        else {
            usersRepository.save(u);
        }
        return "OK";
    }

    private Users attachDetail(Users user) {
        if (user == null)
            return null;
        UserDetail detail =
                userDetailRepository.findByUid(user.getUid()).orElse(null);
        user.setDetail(detail);
        return user;
    }
}
