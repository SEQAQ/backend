package com.backend.seqaq.entity;



import lombok.Data;


import javax.persistence.*;

@Entity
@Data

public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "uid")
    private Long uid;

    @Column(name = "uname")
    private String uname;//nickname
    @Column(name = "account")
    private String account;
    @Column(name = "password")
    private String password;
    @Column(name = "email")
    private String emali;
    @Column(name = "phone")
    private String phone;
    @Column(name = "sex")
    private String sex;

    @Column(name = "rname")
    private String rname;//real name
    @Column(name = "cid")
    private String cid;//colledge's id
    @Column(name = "department")
    private String department;
    @Column(name = "role")
    private String role;

    //0: banned 1: active -1: delete
    private Integer status;//ban or not?
    @Column(name = "follower")
    private Long follower;//follower
    @Column(name = "followed")
    private Long followed;//#fans
    @Transient
    private UserDetail detail;

    public UserDetail getDetail() {
        return detail;
    }

    public void setDetail(UserDetail detail) {
        this.detail = detail;
    }
}
