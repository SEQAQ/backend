package com.backend.seqaq.entity;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "questions")
public class QuestionDetail {
  private ObjectId _id;
  private Long qid;
  // Text based question detail
  private String detail;

  public QuestionDetail() {}

  public QuestionDetail(String detail) {
    this.detail = detail;
  }

  public QuestionDetail(Long qid, String detail) {
    this.qid = qid;
    this.detail = detail;
  }

  @Id
  public ObjectId get_id() {
    return _id;
  }

  public void set_id(ObjectId _id) {
    this._id = _id;
  }

  @Field(name = "qid")
  public Long getQid() {
    return qid;
  }

  public void setQid(Long qid) {
    this.qid = qid;
  }

  @Field(name = "detail")
  public String getDetail() {
    return detail;
  }

  public void setDetail(String detail) {
    this.detail = detail;
  }

  @Override
  public String toString() {
    return "QuestionDetail{" + "_id=" + _id + ", qid=" + qid + ", details='" + detail + '\'' + '}';
  }
}
