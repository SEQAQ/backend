package com.backend.seqaq.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
public class Questions {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "qid")
  private Long qid;

  @Column(name = "tag")
  private String tag;

  @Column(name = "title")
  private String title;

  @Column(name = "ctime")
  private Timestamp ctime;

  @Column(name = "mtime")
  private Timestamp mtime;

  // 0: banned 1: active -1: delete
  @Column(name = "status")
  private Integer status;

  @Column(name = "uid")
  private Long uid;

  @ManyToOne
  @JoinColumn(name = "uid", referencedColumnName = "uid", insertable = false, updatable = false)
  private Users users;

  @Column(name = "follower")
  private Long follower;

  @Transient private QuestionDetail detail;

  public QuestionDetail getDetail() {
    return detail;
  }

  public void setDetail(QuestionDetail detail) {
    this.detail = detail;
  }

  @Override
  public String toString() {
    return "Questions{"
        + "qid="
        + qid
        + ", tag='"
        + tag
        + '\''
        + ", title='"
        + title
        + '\''
        + ", ctime="
        + ctime
        + ", mtime="
        + mtime
        + ", status="
        + status
        + ", uid="
        + uid
        + ", users="
        + users
        + ", follower="
        + follower
        + ", detail="
        + detail
        + '}';
  }
}
