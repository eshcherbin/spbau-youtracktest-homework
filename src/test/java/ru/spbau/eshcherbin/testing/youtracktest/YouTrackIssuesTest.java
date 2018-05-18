package ru.spbau.eshcherbin.testing.youtracktest;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class YouTrackIssuesTest {
  private WebDriver driver;

  @BeforeClass
  public static void setDriverPath() throws Exception {
    System.setProperty(
        "webdriver.gecko.driver",
        Thread.currentThread().getContextClassLoader().getResource("geckodriver").getPath()
    );
  }

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
  }
}
