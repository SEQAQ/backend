package com.backend.seqaq.controller;

import com.backend.seqaq.entity.Users;
import com.backend.seqaq.service.UsersService;
import com.backend.seqaq.util.NotificationMsg;
import com.backend.seqaq.util.Notifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/health")
public class HealthController {
  @Autowired
  UsersService usersService;
  @Autowired
  Notifier notifier;
  @Autowired
  private SimpMessagingTemplate websocket;

  @GetMapping("/")
  public String healthCheck() {
    return "OK!\n114810";
  }

  @GetMapping("/echo/{channel}")
  public void websocketTest(
          @PathVariable(name = "channel") String channelName,
          @RequestParam(name = "data") String data) {
    String channel = "/notify/" + channelName;
    System.out.println(channel + " <- " + data);
    websocket.convertAndSend(channel, data);
  }

  @GetMapping("/notify/{uid}")
  public String notify(@PathVariable("uid") Long uid, @RequestParam(name = "data") String data) {
    Users user = usersService.findById(uid);
    if (user == null) return "No such user";
    notifier.notify(user, new NotificationMsg<>(0, data));
    return "OK";
  }
}
