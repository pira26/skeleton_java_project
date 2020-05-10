package com.skeleton.app.cucumber;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

@TestConfiguration
public class CucumberTestConfig {

  private static final int WM_PORT = 7878;

  @Bean
  public WireMockServer mockServer() {
    return new WireMockServer(options().port(WM_PORT));
  }
}
