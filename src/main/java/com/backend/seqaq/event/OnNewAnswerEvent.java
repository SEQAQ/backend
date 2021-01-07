package com.backend.seqaq.event;

import com.backend.seqaq.entity.Answers;
import org.springframework.context.ApplicationEvent;

public class OnNewAnswerEvent extends ApplicationEvent {
  private Answers answer;

    public OnNewAnswerEvent(Object source) {
        super(source);
    }

    public OnNewAnswerEvent(Object source, Answers answer) {
        super(source);
        this.answer = answer;
    }

  public Answers getAnswer() {
    return answer;
  }

  public void setAnswer(Answers answer) {
    this.answer = answer;
  }
}
