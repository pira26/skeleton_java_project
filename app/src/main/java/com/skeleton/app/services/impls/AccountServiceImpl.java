package com.skeleton.app.services.impls;

import com.skeleton.app.dtos.AccountDTO;
import com.skeleton.app.exceptions.CustomException;
import com.skeleton.app.mappers.AccountMapper;
import com.skeleton.app.models.Account;
import com.skeleton.app.repositories.AccountRepository;
import com.skeleton.app.services.AccountService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.*;

@Service
public class AccountServiceImpl implements AccountService {

  private AccountRepository accountRepository;

  public AccountServiceImpl(AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  @Override
  public String create(final AccountDTO accountDTO) throws CustomException {

    if (!this.accountRepository.existsByEmail(accountDTO.getEmail())) {

      final Account account = AccountMapper.mapToEntity(accountDTO);
      final Account savedAccount = this.accountRepository.save(account);
      return "New User with id: " + savedAccount.getId();
    }
    throw new CustomException("Account already exists", UNPROCESSABLE_ENTITY);
  }

  @Override
  public String signIn(final String email, final String password) throws CustomException {

    final Account account = this.accountRepository.findByEmail(email);

    if (account.getEmail().equals(email) && password.equals(account.getPassword())) {
      // return token
      return "Sign in";
    }

    throw new CustomException("Email or Password doesn't match", NOT_FOUND);
  }

  @Override
  public String delete(final String accountId) throws CustomException {

    final Account account =
        this.accountRepository
            .findById(accountId)
            .orElseThrow(
                () ->
                    new CustomException("Account not found for this id :" + accountId, NOT_FOUND));

    this.accountRepository.delete(account);

    return "Account: " + accountId + " has been deleted :'(";
  }

  @Override
  public AccountDTO getAccount(final String accountId) throws CustomException {

    final Account account =
        this.accountRepository
            .findById(accountId)
            .orElseThrow(() -> new CustomException("Account not found", NOT_FOUND));

    return AccountMapper.mapToDTO(account);
  }

  @Override
  public List<AccountDTO> getAccounts() throws CustomException {

    final List<Account> accounts = this.accountRepository.findAll();

    if (accounts.isEmpty()) {
      throw new CustomException("Accounts not found", NOT_FOUND);
    }

    return accounts.stream().map(AccountMapper::mapToDTO).collect(Collectors.toList());
  }

  @Override
  public AccountDTO getAccountByEmail(final String email) throws CustomException {

    final Account account = this.accountRepository.findByEmail(email);

    if (account != null) {
      return AccountMapper.mapToDTO(account);
    }

    throw new CustomException("Account not found", NOT_FOUND);
  }

  @Override
  public String update(final String accountId, final AccountDTO accountDTO) throws CustomException {

    final Account existingAccount =
        this.accountRepository
            .findById(accountId)
            .orElseThrow(() -> new CustomException("Account not found", NOT_FOUND));
    final Account updatedAccount = AccountMapper.mapToEntity(accountDTO);

    if (!existingAccount.equals(updatedAccount)) {
      this.accountRepository.save(updatedAccount);
      return "Account: " + accountId + " has been updated";
    }

    throw new CustomException("Account has not been modified", NOT_MODIFIED);
  }
}
