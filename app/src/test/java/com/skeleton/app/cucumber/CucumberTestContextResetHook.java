package com.skeleton.app.cucumber;

import io.cucumber.java8.En;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@Ignore
public class CucumberTestContextResetHook extends CucumberStepDefinitions implements En {

  public CucumberTestContextResetHook() {
    Before(CucumberStepDefinitions::initContext);
  }
}
