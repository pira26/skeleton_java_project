package com.skeleton.app.mappers;

import com.skeleton.app.dtos.AccountDTO;
import com.skeleton.app.models.Account;

public class AccountMapper {

  public static AccountDTO mapToDTO(Account account) {
    return AccountDTO.builder()
        .id(account.getId())
        .email(account.getEmail())
        .password(account.getPassword())
        .build();
  }

  public static Account mapToEntity(AccountDTO accountDTO) {
    return Account.builder()
        .email(accountDTO.getEmail())
        .password(accountDTO.getPassword())
        .build();
  }
}
