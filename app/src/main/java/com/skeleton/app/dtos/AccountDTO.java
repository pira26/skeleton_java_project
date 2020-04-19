package com.skeleton.app.dtos;

import lombok.*;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
public class AccountDTO implements Serializable {

  private String id;
  private String email;
  private String password;
}
