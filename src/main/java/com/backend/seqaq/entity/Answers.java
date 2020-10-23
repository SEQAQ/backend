package com.backend.seqaq.entity;


import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
public class Answers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aid")
    private Long aid;

    @Column(name = "ctime")
    private Timestamp ctime;
    @Column(name = "mtime")
    private Timestamp mtime;

    //0: banned 1: active -1: delete
    @Column(name = "status")
    private Integer status;

    @Column(name = "uid")
    private Long uid;
    @Column(name = "qid")
    private Long qid;
    @Column(name = "like")
    private Long like;
    @Column(name = "dislike")
    private Long dislike;
    @Transient
    private AnswerDetail detail;


    @Transient
    @ManyToOne
    @JoinColumn(name = "uid",referencedColumnName = "uid",insertable = false,updatable = false)
    private Users users;

    @Transient
    @ManyToOne
    @JoinColumn(name = "qid",referencedColumnName = "qid",insertable = false,updatable = false)
    private Questions questions;

    public AnswerDetail getDetail() {
        return detail;
    }

    public void setDetail(AnswerDetail detail) {
        this.detail = detail;
    }
}
