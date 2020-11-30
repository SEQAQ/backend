package com.backend.seqaq.dao.impl;

import com.backend.seqaq.dao.UsersDao;
import com.backend.seqaq.entity.UserDetail;
import com.backend.seqaq.entity.Users;
import com.backend.seqaq.repository.UserDetailRepository;
import com.backend.seqaq.repository.UsersRepository;
import com.backend.seqaq.util.exception.RegistrationException;
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

  public static boolean checkAccount(String name) {
    String regExp = "^[^0-9][\\w_]{5,9}$";
    return name.matches(regExp);
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

  public static boolean checkPwd(String pwd) {
    String regExp = "^[\\w_]{6,20}$";
    return pwd.matches(regExp);
  }

  public static boolean checkEmail(String email) {
    String regExp = "^[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*\\.[a-zA-Z0-9]{2,6}$";
    return email.matches(regExp);
  }

  public Users register(Users u) throws RegistrationException {
    String account = u.getAccount();
    String pw = u.getPassword();
    String eml = u.getEmail();
    if (!checkAccount(account)) {
      throw new RegistrationException("Wrong account format");
    }
    if (!checkPwd(pw)) {
      throw new RegistrationException("Wrong password format");
    }
    if (!checkEmail(eml)) {
      throw new RegistrationException("Wrong email format");
    }
    if (findByAccount(u.getAccount()) != null) throw new RegistrationException("Account exists");
    else {
      u.setStatus(1);
      u.setRole("user");
      return usersRepository.save(u);
    }
  }

  @Override
  public Users saveUser(Users user) {
    return usersRepository.save(user);
  }
}
