package com.skeleton.app.cucumber;

import io.cucumber.java8.En;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Objects;

@RunWith(SpringRunner.class)
@Ignore
public class CucumberTestResetMockHook extends CucumberStepDefinitions implements En {

  @Autowired
  private CacheManager cacheManager;

  public CucumberTestResetMockHook() {
    Before(() -> MockitoAnnotations.initMocks(this));

    /**
     * DELETE ALL calls from CrudRepositories, Caches and RestTemplates
     */
    After(() -> {
      // Cache clearing 
      this.cacheManager.getCacheNames().forEach(name -> Objects.requireNonNull(this.cacheManager.getCache(name)).clear());

      // Reset repositories
      this.accountRepository.deleteAll();

      // Reset OAuth2RestTemplate
      // resetToken(/* all OAuth2RestTemplate */); 

//    private void resetToken (OAuth2RestTemplate...oAuth2RestTemplates) {
//      Stream.of(oAuth2RestTemplates).forEach(oAuth2RestTemplate -> oAuth2RestTemplate.getOAuth2ClientContext().setAccessToken(null));
//    }
    });
  }

}