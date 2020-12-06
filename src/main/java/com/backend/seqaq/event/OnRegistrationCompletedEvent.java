package com.backend.seqaq.event;

import com.backend.seqaq.entity.Users;
import org.springframework.context.ApplicationEvent;

public class OnRegistrationCompletedEvent extends ApplicationEvent {
  private String refUrl;
  private Users user;

  public OnRegistrationCompletedEvent(Users user, String refUrl) {
    super(user);
    this.refUrl = refUrl;
    this.user = user;
  }

  public String getRefUrl() {
    return refUrl;
  }

  public void setRefUrl(String refUrl) {
    this.refUrl = refUrl;
  }

  public Users getUser() {
    return user;
  }

  public void setUser(Users user) {
    this.user = user;
  }
}
