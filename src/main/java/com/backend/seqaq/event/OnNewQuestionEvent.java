package com.backend.seqaq.event;

import com.backend.seqaq.entity.Questions;
import org.springframework.context.ApplicationEvent;

public class OnNewQuestionEvent extends ApplicationEvent {
  private Questions question;

  public OnNewQuestionEvent(Object source) {
    super(source);
    this.question = (Questions) source;
  }

  public Questions getQuestion() {
    return question;
  }

  public void setQuestion(Questions question) {
    this.question = question;
  }
}
