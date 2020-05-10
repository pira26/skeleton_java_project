package com.skeleton.app.cucumber.accounts;

import com.skeleton.app.cucumber.CucumberStepDefinitions;
import io.cucumber.java8.En;
import lombok.extern.slf4j.Slf4j;

import static com.skeleton.app.mocks.AccountMock.mockAccountDTO;
import static org.assertj.core.api.Assertions.assertThat;


@Slf4j
public class AccountStepDefinitions extends CucumberStepDefinitions implements En {

  public AccountStepDefinitions() {
    Given("A person wants to create an account", () -> {});

    When("That person fills all information", () -> {
      try {
        post("https://localhost:8000/accounts/sign-up", mockAccountDTO("test@email.fr", "password"));
      } catch (Exception e) {
        log.error("Error when trying to sign up", e);
      }
    });

    Then("An account is created", () -> assertThat(this.accountRepository.findAll().size()).isEqualTo(3));
  }

}
