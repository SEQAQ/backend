package com.backend.seqaq.controller;

import com.backend.seqaq.entity.ConfirmationToken;
import com.backend.seqaq.entity.Users;
import com.backend.seqaq.event.OnRegistrationCompletedEvent;
import com.backend.seqaq.service.ConfirmationTokenService;
import com.backend.seqaq.service.UsersService;
import com.backend.seqaq.util.Message;
import com.backend.seqaq.util.exception.RegistrationException;
import io.swagger.annotations.Api;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.invoke.MethodHandles;

@RestController
@RequestMapping("/users")
@CrossOrigin
@Api
public class UsersController {
  private final int CODE_REG_FAILED = 1;

  private final UsersService usersService;
  private final ApplicationEventPublisher eventPublisher;
  private final ConfirmationTokenService confirmationTokenService;

  private final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

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

  //  @PostMapping("/login")
  //  public String login(
  //      @RequestParam("account") String account, @RequestParam("password") String password) {
  //    return usersService.login(account, password);
  //  }

  @PostMapping(value = "/login")
  public ResponseEntity<Void> login(
      @RequestBody Users loginInfo, HttpServletRequest request, HttpServletResponse response) {
    Subject subject = SecurityUtils.getSubject();
    try {
      // 将用户请求参数封装后，直接提交给Shiro处理
      System.out.println("loginInfo: ");
      System.out.println(loginInfo);

      UsernamePasswordToken token =
          new UsernamePasswordToken(loginInfo.getAccount(), loginInfo.getPassword().toCharArray());

      System.out.println("UsernamePasswordToken: ");
      System.out.println(token);

      subject.login(token);
      // Shiro认证通过后会将user信息放到subject内，生成token并返回
      Users user = (Users) subject.getPrincipal();
      String newToken = usersService.generateJwtToken(user.getAccount());
      System.out.println("Token: ");
      System.out.println(newToken);
      response.setHeader("x-auth-token", newToken);
      response.setHeader("Access-Control-Expose-Headers", "x-auth-token");

      return ResponseEntity.ok().build();
    } catch (AuthenticationException e) {
      // 如果校验失败，shiro会抛出异常，返回客户端失败
      logger.error("User {} login fail, Reason:{}", loginInfo.getAccount(), e.getMessage());
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    } catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }

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

  @GetMapping(value = "/logout")
  public ResponseEntity<Void> logout() {
    Subject subject = SecurityUtils.getSubject();
    if (subject.getPrincipals() != null) {
      Users user = (Users) subject.getPrincipals().getPrimaryPrincipal();
      usersService.deleteLoginInfo(user.getAccount());
    }
    SecurityUtils.getSubject().logout();
    return ResponseEntity.ok().build();
  }
}
