package com.backend.seqaq.service;

import com.backend.seqaq.entity.Users;
import com.backend.seqaq.util.exception.RegistrationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class UsersServiceTest {
  @Autowired
  private UsersService usersService;

  @Test
  void findById() {
    Users users;
    Long uid = 2L;
    users = usersService.findById(uid);
    System.out.println(users);
  }

  @Test
  void findByAccount() {
    Users users;
    String account;
    account = "nihao";
    users = usersService.findByAccount(account);
    if (users != null) assertEquals(account, users.getAccount());
  }

  @Test
  void register() throws RegistrationException {
    Users u = new Users();
    Random random = new Random(1000);
    Integer account = random.nextInt(100000);
    String Account = account.toString();
    Account = "t" + Account;
    System.out.println(Account);
    u.setAccount(Account);
    u.setPassword("sadadwdd");
    u.setEmail("541551@dobby.free");
    System.out.println(usersService.register(u));
  }

  @Test
  void ban_unban_active() {
    Users users = usersService.findById(2L);
    usersService.banUser(2L);
    String result = usersService.checkStatus(users.getAccount());
    assertEquals("用户被禁用", result);
    usersService.unbanUser(2L);
    result = usersService.checkStatus(users.getAccount());
    assertEquals("用户未激活", result);
    users = usersService.findById(2L);
    usersService.activate(users);
    result = usersService.checkStatus(users.getAccount());
    assertEquals("用户已激活", result);
    result = usersService.login(users.getAccount(), users.getPassword());
    assertEquals("Success", result);
  }
}
