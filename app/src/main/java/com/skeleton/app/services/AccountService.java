package com.skeleton.app.services;

import com.skeleton.app.dtos.AccountDTO;
import com.skeleton.app.exceptions.CustomException;

import java.util.List;

public interface AccountService {

  String create(AccountDTO account) throws CustomException;

  String signIn(String email, String password) throws CustomException;

  String delete(String accountId) throws CustomException;

  List<AccountDTO> getAccounts() throws CustomException;

  AccountDTO getAccount(String id) throws CustomException;

  AccountDTO getAccountByEmail(String email) throws CustomException;

  String update(String accountId, AccountDTO account) throws CustomException;
}
