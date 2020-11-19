package com.backend.seqaq.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "userandques")
public class UserAndQues {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "uid", referencedColumnName = "uid", insertable = false, updatable = false)
  private Users users;

  @ManyToOne
  @JoinColumn(name = "qid", referencedColumnName = "qid", insertable = false, updatable = false)
  private Questions questions;

  @Column(name = "uid")
  private Long uid;

  @Column(name = "qid")
  private Long qid;
}
