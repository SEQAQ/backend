package com.backend.seqaq.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Followers {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @Transient
  @ManyToOne
  @JoinColumn(name = "uid1", referencedColumnName = "uid", insertable = false, updatable = false)
  private Users users1;

  @Transient
  @ManyToOne
  @JoinColumn(name = "uid2", referencedColumnName = "uid", insertable = false, updatable = false)
  private Users users2;

  @Column(name = "uid1")
  private Long uid1;

  @Column(name = "uid2")
  private Long uid2;
}
