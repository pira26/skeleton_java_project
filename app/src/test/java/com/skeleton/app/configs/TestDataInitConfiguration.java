package com.skeleton.app.configs;

import com.skeleton.app.repositories.AccountRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Arrays;

import static com.skeleton.app.mocks.AccountMock.mockAccount;

@Configuration
@Transactional
@Profile("test")
public class TestDataInitConfiguration {

  private static final Logger LOGGER = LoggerFactory.getLogger(TestDataInitConfiguration.class);

  @Autowired private AccountRepository accountRepository;

  @PostConstruct
  public void loadDemoData() {

    if (this.accountRepository.count() == 0) {
      LOGGER.info("[TEST] init demo data for local testing. Load default accounts");

      this.accountRepository.saveAll(
          Arrays.asList(
                  mockAccount("1", "email@test.fr" ,"password"),
                  mockAccount("2", "test@email.com", "still weak password")));
    }
  }
}
