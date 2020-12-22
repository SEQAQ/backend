package com.backend.seqaq.listener;

import com.backend.seqaq.entity.Replies;
import com.backend.seqaq.entity.Users;
import com.backend.seqaq.event.OnNewReplyEvent;
import com.backend.seqaq.service.FollowersService;
import com.backend.seqaq.util.NotificationMsg;
import com.backend.seqaq.util.Notifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class NewReplyListener implements ApplicationListener<OnNewReplyEvent> {
  @Autowired
  FollowersService followersService;
  @Autowired
  Notifier notifier;

  @Override
  public void onApplicationEvent(OnNewReplyEvent onNewAnswerEvent) {
    Replies reply = onNewAnswerEvent.getReplies();
    NotificationMsg<Replies> msg = new NotificationMsg<>(NotificationMsg.TYPE_COMMENT, reply);
    if (reply.getDtype().equals(Replies.TYPE_ANSWER)) {
      Users answerer = reply.getAnswers().getUsers();
      notifier.notify(answerer, msg);
    } else if (reply.getDtype().equals(Replies.TYPE_REPLY)) {
      Users target = reply.getReplies().getUsers();
      notifier.notify(target, msg);
    }
  }
}
