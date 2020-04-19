package com.skeleton.app.accounts;

import com.skeleton.app.repositories.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static com.skeleton.app.mocks.AccountMock.mockAccount;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class AccountRepositoryTest {

  @Autowired private AccountRepository accountRepository;

  @Test
  public void assert_find_all_accounts() {
    assertThat(this.accountRepository.findAll().size()).isEqualTo(2);
  }

  @Test
  public void assert_find_account_by_account_id() {
    assertThat(this.accountRepository.findById("1"))
        .isEqualTo(Optional.of(mockAccount("1", "email@test.fr", "password")));
  }

  @Test
  public void assert_empty_find_account_by_account_id() {
    assertThat(this.accountRepository.findById("3")).isEqualTo(Optional.empty());
  }

  @Test
  public void assert_find_account_by_email() {
    assertThat(this.accountRepository.findByEmail("test@email.com"))
        .isEqualTo(mockAccount("2", "test@email.com", "still weak password"));
  }

  @Test
  public void assert_exist_by_email_is_true() {
    assertThat(this.accountRepository.existsByEmail("test@email.com")).isTrue();
  }

  @Test
  public void assert_exist_by_email_is_false() {
    assertThat(this.accountRepository.existsByEmail("test@m.fr")).isFalse();
  }

  @Test
  public void assert_delete_account_successfully() {
    this.accountRepository.delete(mockAccount("1", "email@test.fr", "password"));
    assertAll(
        "account",
        () -> assertThat(this.accountRepository.findAll().size()).isEqualTo(1),
        () -> assertThat(this.accountRepository.existsByEmail("email@test.fr")).isFalse(),
        () -> assertThat(this.accountRepository.findById("1")).isEqualTo(Optional.empty()));
  }

  @Test
  public void assert_delete_account_failure() {
    this.accountRepository.delete(mockAccount("3", "e@e.fr", "password"));
    assertAll(
            "account",
            () -> assertThat(this.accountRepository.findAll().size()).isEqualTo(2),
            () -> assertThat(this.accountRepository.existsByEmail("e@e.fr")).isFalse(),
            () -> assertThat(this.accountRepository.findById("3")).isEqualTo(Optional.empty()));
  }
}
