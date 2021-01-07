package com.backend.seqaq.entity;


import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Like_record {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lid")
    private Long lid;

    @Column(name = "aid")
    private Long aid;

    @Column(name = "uid")
    private Long uid;
}
