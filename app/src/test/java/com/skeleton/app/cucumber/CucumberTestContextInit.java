package com.skeleton.app.cucumber;

import io.cucumber.java8.En;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;


@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestPropertySource(locations = "classpath:application.yml")
@SpringBootTest(classes = CucumberTestConfig.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class CucumberTestContextInit implements En {

  public CucumberTestContextInit() {
    Before(() -> {});
  }

}
