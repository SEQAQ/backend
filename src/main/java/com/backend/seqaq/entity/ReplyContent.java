package com.backend.seqaq.entity;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;

@Document(collection = "replies")
public class ReplyContent {
  private ObjectId _id;
  private Long rid;
  private String content;

  @Id
  public ObjectId get_id() {
    return _id;
  }

  public void set_id(ObjectId _id) {
    this._id = _id;
  }

  @Field(name = "rid")
  public Long getRid() {
    return rid;
  }

  public void setRid(Long rid) {
    this.rid = rid;
  }

  @Field(name = "text")
  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  @Override
  public String toString() {
    return "{\"_class\":\"ReplyContent\", "
        + "\"_id\":"
        + (_id == null ? "null" : _id)
        + ", "
        + "\"rid\":"
        + (rid == null ? "null" : "\"" + rid + "\"")
        + ", "
        + "\"content\":"
        + (content == null ? "null" : "\"" + content + "\"")
        + "}";
  }
}
