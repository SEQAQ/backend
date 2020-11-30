package com.backend.seqaq.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "confirmation_token")
public class ConfirmationToken {
    private Long tid;
    private String token;
    private Date createdDate;
    private Users user;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tid")
    public Long getTid() {
        return tid;
    }

    public void setTid(Long tid) {
        this.tid = tid;
    }

    @Column(name = "token")
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Column(name = "created_date")
    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @OneToOne
    @JoinColumn(name = "uid", referencedColumnName = "uid")
    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }
}
