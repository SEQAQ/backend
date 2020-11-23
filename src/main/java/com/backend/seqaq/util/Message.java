package com.backend.seqaq.util;

public class Message<T> {
  private static final int CODE_SUCCESS = 0;
  private static final int CODE_FAILURE = 1;

  private int code = 0;
  // Message for the user, describing the response
  private String message = "OK";
  private T data;

  public Message() {
  }

  public Message(int code, String message, T data) {
    this.code = code;
    this.message = message;
    this.data = data;
  }

  public Message(int code, String message) {
    this.code = code;
    this.message = message;
  }

  public Message(T data) {
    this.data = data;
  }

  public int getCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }
}
