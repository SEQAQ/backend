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

    @Column(name = "ctime")
    private Timestamp ctime;
    @Column(name = "mtime")
    private Timestamp mtime;
    @Column(name = "status")
    private Integer status;

    @OneToOne
    @JoinColumn(name = "uid",referencedColumnName = "uid",insertable = false,updatable = false)
    private Users users;

    @Column(name = "follower")
    private Long follower;

}
