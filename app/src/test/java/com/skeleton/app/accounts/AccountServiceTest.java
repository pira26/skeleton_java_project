package com.skeleton.app.accounts;

import com.skeleton.app.dtos.AccountDTO;
import com.skeleton.app.models.Account;
import com.skeleton.app.repositories.AccountRepository;
import com.skeleton.app.services.impls.AccountServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static com.skeleton.app.mocks.AccountMock.mockAccount;
import static com.skeleton.app.mocks.AccountMock.mockAccountDTO;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
@SpringBootTest
@ActiveProfiles("test")
public class AccountServiceTest {

  @Mock private AccountRepository mockAccountRepository;

  private AccountServiceImpl mockAccountService;

  private static AccountDTO mockAccountDTO = mockAccountDTO("test@email.fr", "password");
  private static Account account = mockAccount("1", "test@email.fr", "password");

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    this.mockAccountService = new AccountServiceImpl(this.mockAccountRepository);
  }

  // FIXME incorrect repository mock with Mockito, seem to not accessing to H2 database
  @Test
  public void create_account_successfully() {

    given(
            this.mockAccountRepository.save(
                Account.builder().email("test@eamil.fr").password("password").build()))
        .willReturn(account);

    //
    //    doReturn(account)
    //        .when(this.mockAccountRepository)
    //        .save(Account.builder().email("test@eamil.fr").password("password").build());

    this.mockAccountService.create(mockAccountDTO);

    then(this.mockAccountRepository).should().save(account);
    assertAll(
        "account",
        () ->
            assertThat(this.mockAccountService.create(mockAccountDTO))
                .isEqualTo("New User with id: 1"),
        () -> assertThat(this.mockAccountRepository.findAll().size()).isEqualTo(3),
        () -> assertThat(this.mockAccountRepository.getOne(account.getId())).isEqualTo(account));
  }
}
