package com.backend.seqaq.entity;

public class AnswerwithTag {
  private Answers answers;
  private boolean tag;

  public Answers getAnswers() {
    return answers;
  }

  public void setAnswers(Answers answers) {
    this.answers = answers;
  }

  public void setTag(boolean tag) {
    this.tag = tag;
  }

  public boolean isTag() {
    return tag;
  }
}
