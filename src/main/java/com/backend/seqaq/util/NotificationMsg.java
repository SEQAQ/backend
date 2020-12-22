package com.backend.seqaq.util;

public class NotificationMsg<T> {
  public static final int TYPE_ANSWER = 0;
  public static final int TYPE_QUESTION = 1;
  public static final int TYPE_COMMENT = 2;

  private int type;
  private T data;

  public NotificationMsg(int type, T data) {
    this.type = type;
    this.data = data;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }
}
