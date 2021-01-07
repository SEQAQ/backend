package com.backend.seqaq.event;

import com.backend.seqaq.entity.Replies;
import org.springframework.context.ApplicationEvent;

public class OnNewReplyEvent extends ApplicationEvent {
  private Replies replies;

  public OnNewReplyEvent(Replies reply) {
    super(reply);
    this.replies = reply;
  }

  public Replies getReplies() {
    return replies;
  }

  public void setReplies(Replies replies) {
    this.replies = replies;
  }
}
