package com.backend.seqaq.service.impl;

import com.backend.seqaq.dao.UsersDao;
import com.backend.seqaq.entity.*;
import com.backend.seqaq.service.UsersService;
import com.backend.seqaq.util.exception.RegistrationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {
  @Autowired private UsersDao usersDao;

  public Users findById(Long id) {
    return usersDao.findById(id);
  }

  public Users findByAccount(String account) {
    return usersDao.findByAccount(account);
  }

  public Users register(Users u) throws RegistrationException {
    UserDetail userDetail = new UserDetail();
    userDetail.setUid(u.getUid());
    userDetail.setAvatar("");
    u.setDetail(userDetail);
    return usersDao.register(u);
  }

  public String banUser(Long uid) {
    Users users = usersDao.findById(uid);
    if (users == null) return "Error";
    else {
      users.setStatus(0);
      usersDao.saveUser(users);
      return "OK";
    }
  }

  public String unbanUser(Long uid) {
    Users users = usersDao.findById(uid);
    if (users == null) return "Error";
    else {
      users.setStatus(1);
      usersDao.saveUser(users);
      return "OK";
    }
  }

  public String login(String account, String password) {
    Users users = usersDao.findByAccount(account);
    if (users == null) return "User doesn't exist";
    else if (!users.getPassword().equals(password)) return "Password is wrong";
    else if (users.getStatus() == 1) return "用户待激活";
    else if (users.getStatus() == 2) return "Success";
    else return "用户被禁用或被删除";
  }

  public String checkStatus(String account) {
    Users users = usersDao.findByAccount(account);
    if (users == null) return "用户不存在";
    int status = users.getStatus();
    String result = null;
    switch (status) {
      case 1:
        result = "用户未激活";
        break;
      case Users.STAT_ACTIVATED:
        result = "用户已激活";
        break;
      case 0:
        result = "用户被禁用";
        break;
      case -1:
        result = "用户被删除";
        break;
    }
    return result;
  }

  public Page<Users> findAll(Pageable pageable) {
    return usersDao.findAll(pageable);
  }

  public List<Users> findAll() {
    return usersDao.findAll();
  }

  public Long editUser(Users users, int isChanged) {
    Long result = usersDao.saveForEdit(users, isChanged);
    return result;
  }

  @Override
  public void activate(Users user) {
    user.setStatus(Users.STAT_ACTIVATED);
    usersDao.saveUser(user);
  }

  @Override
  public UserBean getUser(String username) {
    Users users = usersDao.findByAccount(username);
    if (users == null) {
      return null;
    }

    UserBean user = new UserBean();
    user.setUsername(username);
    user.setPassword(users.getPassword());
    user.setRole(users.getRole());
    user.setPermission(users.getPermission());

    return user;
  }
}
