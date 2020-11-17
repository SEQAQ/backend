package com.backend.seqaq.entity;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;

@Document(collection = "answers")
public class AnswerDetail {
  private ObjectId _id;
  private Long aid;
  // Markdown source code of the answer
  private String mdText;

  public AnswerDetail() {}

  public AnswerDetail(Long aid, String mdText) {
    this.aid = aid;
    this.mdText = mdText;
  }

  @Id
  public ObjectId get_id() {
    return _id;
  }

  public void set_id(ObjectId _id) {
    this._id = _id;
  }

  @Field(name = "aid")
  public Long getAid() {
    return aid;
  }

  public void setAid(Long aid) {
    this.aid = aid;
  }

  @Field(name = "text")
  public String getMdText() {
    return mdText;
  }

  public void setMdText(String mdText) {
    this.mdText = mdText;
  }

  @Override
  public String toString() {
    return "{" + "_id:" + _id + ", aid:" + aid + ", mdText:'" + mdText + '\'' + '}';
  }
}
