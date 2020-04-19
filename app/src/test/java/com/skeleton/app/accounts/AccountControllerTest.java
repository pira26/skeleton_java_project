package com.skeleton.app.accounts;

import com.skeleton.app.dtos.AccountDTO;
import com.skeleton.app.exceptions.CustomException;
import com.skeleton.app.services.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static com.skeleton.app.mocks.AccountMock.mockSavedAccountDTO;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
public class AccountControllerTest {

  @Autowired private MockMvc mockMvc;

  @MockBean private AccountService accountService;

  @Test
  public void test_successfully_get_account_by_id() throws Exception {

    final AccountDTO mockAccount = mockSavedAccountDTO("1", "email@test.fr", "password");

    doReturn(mockAccount).when(this.accountService).getAccount(mockAccount.getId());

    this.mockMvc
        .perform(get("/accounts/{id}", mockAccount.getId()))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(mockAccount.getId()))
        .andExpect(jsonPath("$.email").value(mockAccount.getEmail()))
        .andExpect(jsonPath("$.password").value(mockAccount.getPassword()));
  }

  // FIXME exception thrown
  @Test
  public void test_not_found_get_account_by_id() throws Exception {

    doThrow(new CustomException("ERROR", NOT_FOUND)).when(this.accountService).getAccount("1");

    this.mockMvc.perform(get("/accounts/{id}", "1")).andExpect(status().isNotFound());
  }

  // FIXME exception thrown
  @Test
  public void test_error_get_account_by_id() throws Exception {
    final AccountDTO mockAccount = mockSavedAccountDTO("1","email@test.fr", "password");

    doReturn(mockAccount).when(this.accountService).getAccount(mockAccount.getId());

    this.mockMvc.perform(get("/accounts/{id}", "1")).andExpect(status().isInternalServerError());
  }
}
