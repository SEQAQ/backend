package com.backend.seqaq.listener;

import com.backend.seqaq.entity.*;
import com.backend.seqaq.event.OnNewAnswerEvent;
import com.backend.seqaq.service.FollowersService;
import com.backend.seqaq.service.QuesService;
import com.backend.seqaq.service.UserAndQuesService;
import com.backend.seqaq.util.NotificationMsg;
import com.backend.seqaq.util.Notifier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class NewAnswerListener implements ApplicationListener<OnNewAnswerEvent> {
  @Autowired QuesService quesService;
  @Autowired UserAndQuesService ufqService;
  @Autowired FollowersService followersService;
  @Autowired Notifier notifier;

  @Override
  public void onApplicationEvent(OnNewAnswerEvent onNewAnswerEvent) {
    Answers answer = onNewAnswerEvent.getAnswer();
    NotificationMsg<Answers> msg = new NotificationMsg<>(NotificationMsg.TYPE_ANSWER, answer);
    // Notify the questioner and followers of the corresponding question
    Questions question = quesService.findById(answer.getQid());
    Users user = question.getUsers();
    notifier.notify(user, msg);
    List<UserAndQues> followers = ufqService.findAllUsersByQid(question.getQid());
    for (var iter : followers) {
      var follower = iter.getUsers();
      notifier.notify(follower, msg);
    }
    // Notify the followers of the answerer
    var answererFollowers =
        followersService.findAllFollowerByUid(answer.getUid()).stream()
            .map(Followers::getUsers2)
            .collect(Collectors.toList());
    notifier.notify(answererFollowers, msg);
  }
}
