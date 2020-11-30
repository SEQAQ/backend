package com.backend.seqaq.service;

import com.backend.seqaq.entity.Users;
import com.backend.seqaq.util.exception.RegistrationException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UsersServiceTest {
  @Autowired private UsersService usersService;

  @Test
  void findById() {
    Users users;
    Long uid = 2L;
    users = usersService.findById(uid);
    System.out.println(users);
  }

  @Test
  void findByAccount() {}

  @Test
  void register() throws RegistrationException {
    Users u = new Users();
    u.setAccount("acc1522");
    u.setPassword("sadadwdd");
    u.setEmail("541551");
    System.out.println(usersService.register(u));
  }
}
