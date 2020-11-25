package com.backend.seqaq.dao.impl;

import com.backend.seqaq.dao.UsersDao;
import com.backend.seqaq.entity.UserDetail;
import com.backend.seqaq.entity.Users;
import com.backend.seqaq.repository.UserDetailRepository;
import com.backend.seqaq.repository.UsersRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    String account = u.getAccount();
    String pw = u.getPassword();
    String eml = u.getEmail();
    if (!checkAccount(account)) {
      return "Account unformal";
    }
    if (!checkPwd(pw)) {
      return "Password unformal";
    }
    if (!checkEmail(eml)) {
      return "Email unformal";
    }
    if (findByAccount(u.getAccount()) != null) return "Account Exists";
    else {
      u.setStatus(1);
      u.setRole("user");
      usersRepository.save(u);
      return "OK";
    }
  }

  public List<Users> findAllByUnameContaining(String text) {
    return usersRepository.findAllByUnameContaining(text);
  }

  private Users attachDetail(Users user) {
    if (user == null) return null;
    UserDetail detail = userDetailRepository.findByUid(user.getUid()).orElse(null);
    user.setDetail(detail);
    return user;
  }

  public static boolean checkAccount(String name) {
    String regExp = "^[^0-9][\\w_]{5,9}$";
    if (name.matches(regExp)) {
      return true;
    } else {
      return false;
    }
  }

  public static boolean checkPwd(String pwd) {
    String regExp = "^[\\w_]{6,20}$";
    if (pwd.matches(regExp)) {
      return true;
    }
    return false;
  }

  public static boolean checkEmail(String email) {
    String regExp = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$";
    if (email.matches(regExp)) {
      return true;
    }
    return false;
  }
}
