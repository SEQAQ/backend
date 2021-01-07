package com.backend.seqaq.util;

import com.backend.seqaq.entity.Users;
import com.backend.seqaq.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Notifier {
  @Autowired private SimpMessagingTemplate websocket;
  @Autowired private UsersService usersService;

  private static String buildDst(Users user) {
    return "/notify/user" + user.getUid();
  }

  public <T> void notify(Users user, NotificationMsg<T> message) {
    System.out.println("notify => " + user.getUid());
    websocket.convertAndSend(buildDst(user), message);
  }

  public <T> void notify(List<Users> userList, NotificationMsg<T> msg) {
    for (var user : userList) {
      notify(user, msg);
    }
  }
}
