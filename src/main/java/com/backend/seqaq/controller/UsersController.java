package com.backend.seqaq.controller;

import com.backend.seqaq.entity.Users;
import com.backend.seqaq.service.UsersService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin
@Api
public class UsersController {
  @Autowired private UsersService usersService;

  @PostMapping("/register")
  public String save(@RequestBody Users users) {
    return usersService.register(users);
  }

  @PostMapping("/ban")
  public String ban(@RequestParam("uid") Long uid) {
    return usersService.banUser(uid);
  }

  @PostMapping("/unban")
  public String unban(@RequestParam("uid") Long uid) {
    return usersService.unbanUser(uid);
  }

  @GetMapping("/findbyid")
  public Users findById(@RequestParam("uid") Long uid) {
    System.out.println(usersService.findById(uid));
    return usersService.findById(uid);
  }

  @GetMapping("/findbyaccount")
  public Users findByAccount(@RequestParam("account") String account) {
    return usersService.findByAccount(account);
  }

  @PostMapping("/login")
  public String login(
      @RequestParam("account") String account, @RequestParam("password") String password) {
    return usersService.login(account, password);
  }

  @PostMapping("/checkstatus")
  public String checkstatus(@RequestParam("account") String account) {
    return usersService.checkStatus(account);
  }
}
