package com.backend.seqaq.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
public class Replies {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "rid")
  private Long rid;

  @Column(name = "ctime")
  private Timestamp ctime;

  // 0: banned 1: active -1: delete
  @Column(name = "stat")
  private Integer status;

  @Column(name = "love")
  private Long like;

  @Column(name = "dislike")
  private Long dislike;

  @Column(name = "uid")
  private Long uid;

  @ManyToOne
  @JoinColumn(name = "uid", referencedColumnName = "uid", insertable = false, updatable = false)
  private Users users;

  @Column(name = "did")
  private Long did;

  // 0:answers,1:replies
  public static final Integer TYPE_ANSWER = 0;
  public static final Integer TYPE_REPLY = 1;

  @Column(name = "dtype")
  private Integer dtype;

  @Transient private Answers answers;

  @Transient private Replies replies;

  @Transient private ReplyContent content;

  public Long getRid() {
    return rid;
  }

  public void setRid(Long rid) {
    this.rid = rid;
  }

  public ReplyContent getContent() {
    return content;
  }

  public void setContent(ReplyContent content) {
    this.content = content;
  }
}
