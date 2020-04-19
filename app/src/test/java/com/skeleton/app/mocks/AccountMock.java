package com.skeleton.app.mocks;

import com.skeleton.app.dtos.AccountDTO;
import com.skeleton.app.models.Account;

public class AccountMock {

  public static AccountDTO mockAccountDTO(String email, String password) {
    return AccountDTO.builder().email(email).password(password).build();
  }

  public static AccountDTO mockSavedAccountDTO(String id, String email, String password) {
    return AccountDTO.builder().id(id).email(email).password(password).build();
  }

  public static Account mockAccount(String id, String email, String password) {
    return Account.builder().id(id).email(email).password(password).build();
  }
}
