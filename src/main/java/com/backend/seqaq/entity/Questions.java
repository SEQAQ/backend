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

    //0: banned 1: active -1: delete
    @Column(name = "status")
    private Integer status;

    @Column(name = "uid")
    private Long uid;

    @Transient
    @ManyToOne
    @JoinColumn(name = "uid",referencedColumnName = "uid",insertable = false,updatable = false)
    private Users users;

    @Column(name = "follower")
    private Long follower;

}
