package com.backend.seqaq.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Users {
  public static final int STAT_ACTIVATED = 2;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "uid")
  private Long uid;

  @Column(name = "uname")
  private String uname; // nickname

  @Column(name = "account")
  private String account;

  @Column(name = "password")
  @JsonIgnore
  private String password;

  @Column(name = "email")
  private String email;

  @Column(name = "phone")
  private String phone;

  @Column(name = "sex")
  private String sex;

  @Column(name = "rname")
  private String rname; // real name

  @Column(name = "cid")
  private String cid; // colledge's id

  @Column(name = "department")
  private String department;

  @Column(name = "role")
  private String role;

  @Column(name = "permission")
  private String permission;

  @Column(name = "exp")
  private Integer exp;

  @Column(name = "lev")
  private Integer level;

  // 0: banned 1: 待激活 -1: delete 2: active
  @Column(name = "stat")
  private Integer status; // ban or not?

  @Column(name = "follower")
  private Long follower; // follower

  @Column(name = "followed")
  private Long followed; // #fans

  @Transient private UserDetail detail;

  @OneToOne(mappedBy = "user")
  @JsonIgnore
  private ConfirmationToken token;

  public ConfirmationToken getToken() {
    return this.token;
  }

  public void setToken(ConfirmationToken token) {
    this.token = token;
  }

  public UserDetail getDetail() {
    return detail;
  }

  public void setDetail(UserDetail detail) {
    this.detail = detail;
  }

  public Long getUid() {
    return uid;
  }

  public void setUid(Long uid) {
    this.uid = uid;
  }

  public String getUname() {
    return uname;
  }

  public void setUname(String uname) {
    this.uname = uname;
  }

  public String getAccount() {
    return account;
  }

  public void setAccount(String account) {
    this.account = account;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getSex() {
    return sex;
  }

  public void setSex(String sex) {
    this.sex = sex;
  }

  public String getRname() {
    return rname;
  }

  public void setRname(String rname) {
    this.rname = rname;
  }

  public String getCid() {
    return cid;
  }

  public void setCid(String cid) {
    this.cid = cid;
  }

  public String getDepartment() {
    return department;
  }

  public void setDepartment(String department) {
    this.department = department;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public String getPermission() {
    return permission;
  }

  public void setPermission(String permission) {
    this.permission = permission;
  }

  public Integer getStatus() {
    return status;
  }

  public void setStatus(Integer status) {
    this.status = status;
  }

  public Long getFollower() {
    return follower;
  }

  public void setFollower(Long follower) {
    this.follower = follower;
  }

  public Long getFollowed() {
    return followed;
  }

  public void setFollowed(Long followed) {
    this.followed = followed;
  }
}
