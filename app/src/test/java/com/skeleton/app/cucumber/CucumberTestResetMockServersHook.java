package com.skeleton.app.cucumber;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@Ignore
public class CucumberTestResetMockServersHook extends CucumberStepDefinitions {

  @Before
  public void start() {
    if (!this.mockServer.isRunning()) {
      this.mockServer.start();
    }
  }

  @After
  public void resetMockServer() {
    this.mockServer.resetAll();
    this.mockServer.resetScenarios();
  }
}
