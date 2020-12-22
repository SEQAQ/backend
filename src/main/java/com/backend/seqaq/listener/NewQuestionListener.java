package com.backend.seqaq.listener;

import com.backend.seqaq.entity.Followers;
import com.backend.seqaq.entity.Questions;
import com.backend.seqaq.entity.Users;
import com.backend.seqaq.event.OnNewQuestionEvent;
import com.backend.seqaq.service.FollowersService;
import com.backend.seqaq.util.NotificationMsg;
import com.backend.seqaq.util.Notifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class NewQuestionListener implements ApplicationListener<OnNewQuestionEvent> {
  @Autowired FollowersService followersService;
  @Autowired Notifier notifier;

  @Override
  public void onApplicationEvent(OnNewQuestionEvent onNewAnswerEvent) {
    Questions question = onNewAnswerEvent.getQuestion();
    NotificationMsg<Questions> msg = new NotificationMsg<>(NotificationMsg.TYPE_QUESTION, question);
    // Notify the followers of the questioner
    Users questioner = question.getUsers();
    var followers =
        followersService.findAllFollowerByUid(questioner.getUid()).stream()
            .map(Followers::getUsers2)
            .collect(Collectors.toList());
    notifier.notify(followers, msg);
  }
}
