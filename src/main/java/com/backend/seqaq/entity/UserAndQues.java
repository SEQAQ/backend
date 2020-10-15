package com.backend.seqaq.entity;

import lombok.Data;

import javax.persistence.*;
@Entity
@Data
public class UserAndQues {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Transient
    @ManyToOne
    @JoinColumn(name = "uid",referencedColumnName = "uid",insertable = false,updatable = false)
    private Users users;

    @Transient
    @ManyToOne
    @JoinColumn(name = "qid",referencedColumnName = "qid",insertable = false,updatable = false)
    private Questions questions;
    @Column(name = "uid")
    private Long uid;
    @Column(name = "qid")
    private Long qid;
}
