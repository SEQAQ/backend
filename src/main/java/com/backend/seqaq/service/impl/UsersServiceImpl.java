package com.backend.seqaq.service.impl;

import com.backend.seqaq.config.JwtUtils;
import com.backend.seqaq.dao.UsersDao;
import com.backend.seqaq.entity.Users;
import com.backend.seqaq.service.UsersService;
import com.backend.seqaq.util.exception.RegistrationException;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//import java.util.Arrays;
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
    return usersDao.register(u);
  }

  public String banUser(Long uid) {
    Users users = usersDao.findById(uid);
    if (users == null) return "Error";
    else {
      users.setStatus(0);
      return "OK";
    }
  }

  public String unbanUser(Long uid) {
    Users users = usersDao.findById(uid);
    if (users == null) return "Error";
    else {
      users.setStatus(1);
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

  @Override
  public void activate(Users user) {
    user.setStatus(Users.STAT_ACTIVATED);
    usersDao.saveUser(user);
  }

  public String generateJwtToken(String account) {
    String salt = JwtUtils.generateSalt();
    Users user = usersDao.findByAccount(account);
    if(user == null) return "Error";
    user.setSalt(salt);
    return JwtUtils.sign(account, salt, 3600);
  }

  public Users getJwtTokenInfo(String account) {
    return findByAccount(account);
  }

  public void deleteLoginInfo(String account) {
    Users user = usersDao.findByAccount(account);
    user.setSalt("");
  }

  public Users getUserInfo(String account) {
    Users user = new Users();
    user.setEncryptPwd(new Sha256Hash(user.getPassword(), user.getSalt()).toHex());
    return user;
  }

  /**
   * 获取用户角色列表，强烈建议从缓存中获取
   * @param uid
   * @return
   */
  public List<String> getUserRoles(Long uid){
    Users user = usersDao.findById(uid);
//    return Arrays.asList("admin");
    return user.getRoles();
  }
}
