package com.skeleton.app.models;

import com.skeleton.app.commons.AbstractUuidEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "ACCOUNT")
@Getter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class Account extends AbstractUuidEntity {

  @Column(name = "EMAIL", unique = true, nullable = false)
  private String email;

  @Column(name = "PASSWORD", nullable = false)
  private String password;

  @Builder
  public Account(String id, String email, String password) {
    this.id = id;
    this.email = email;
    this.password = password;
  }
}
