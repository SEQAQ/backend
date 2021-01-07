package com.backend.seqaq.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
public class Hot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hid")
    private Long hid;

    @Column(name = "created_date")
    private Timestamp ctime;

    @Column(name = "qid")
    private Long qid;

    @Column(name = "uid")
    private Long uid;
}