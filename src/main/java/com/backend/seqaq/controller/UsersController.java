package com.backend.seqaq.controller;

import com.backend.seqaq.config.JWTUtil;
import com.backend.seqaq.config.UnauthorizedException;
import com.backend.seqaq.entity.ConfirmationToken;
import com.backend.seqaq.entity.ResponseBean;
import com.backend.seqaq.entity.UserBean;
import com.backend.seqaq.entity.Users;
import com.backend.seqaq.event.OnRegistrationCompletedEvent;
import com.backend.seqaq.service.ConfirmationTokenService;
import com.backend.seqaq.service.UsersService;
import com.backend.seqaq.util.Message;
import com.backend.seqaq.util.exception.RegistrationException;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin
@Api
public class UsersController {
  private final int CODE_REG_FAILED = 1;

  private final UsersService usersService;
  private final ApplicationEventPublisher eventPublisher;
  private final ConfirmationTokenService confirmationTokenService;

  public UsersController(
      UsersService usersService,
      ApplicationEventPublisher eventPublisher,
      ConfirmationTokenService confirmationTokenService) {
    this.usersService = usersService;
    this.eventPublisher = eventPublisher;
    this.confirmationTokenService = confirmationTokenService;
  }

  @PostMapping("/register")
  public Message<Users> save(@RequestBody Users users) {
    try {
      Users savedUser = usersService.register(users);
      eventPublisher.publishEvent(new OnRegistrationCompletedEvent(savedUser, ""));
      return new Message<>(0, "OK");
    } catch (RegistrationException e) {
      return new Message<>(CODE_REG_FAILED, e.getMessage());
    }
  }

  @PostMapping("/edit")
  public String edit(@RequestBody Users users) {
    usersService.editUser(users);
    return "OK";
  }

  @PostMapping("/ban")
  @RequiresRoles("admin")
  public String ban(@RequestParam("uid") Long uid) {
    return usersService.banUser(uid);
  }

  @PostMapping("/unban")
  @RequiresRoles("admin")
  public String unban(@RequestParam("uid") Long uid) {
    return usersService.unbanUser(uid);
  }

  @GetMapping("/findbyid")
  public Users findById(@RequestParam("uid") Long uid) {
    System.out.println(usersService.findById(uid));
    return usersService.findById(uid);
  }

  @GetMapping("/findAllbyPage")
  public Page<Users> findAllByPage(
      @RequestParam("page") Integer page, @RequestParam("size") Integer size) {
    Pageable pageable = PageRequest.of(page, size);
    return usersService.findAll(pageable);
  }

  @GetMapping("/findAll")
  public List<Users> findAll() {
    return usersService.findAll();
  }

  @GetMapping("/findbyaccount")
  public Users findByAccount(@RequestParam("account") String account) {
    return usersService.findByAccount(account);
  }

  @PostMapping("/login")
  public ResponseBean login(
      @RequestParam("username") String username, @RequestParam("password") String password) {
    UserBean userBean = usersService.getUser(username);
    if (userBean.getPassword().equals(password)) {
      return new ResponseBean(200, "Login success", JWTUtil.sign(username, password));
    } else {
      throw new UnauthorizedException();
    }
  }
  //  public String login(
  //      @RequestParam("account") String account, @RequestParam("password") String password) {
  //    return usersService.login(account, password);
  //  }

  @PostMapping("/checkstatus")
  public String checkstatus(@RequestParam("account") String account) {
    return usersService.checkStatus(account);
  }

  @GetMapping("/activate")
  public Message<String> confirmRegistration(@RequestParam("token") String tokenString) {
    ConfirmationToken token = confirmationTokenService.findByToken(tokenString);
    Users user = token.getUser();
    if (user == null) return new Message<>(1, "invalid token");
    usersService.activate(user);
    return new Message<>("OK!");
  }

  @RequestMapping(path = "/401")
  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  public ResponseBean unauthorized() {
    return new ResponseBean(401, "Unauthorized", null);
  }
}
