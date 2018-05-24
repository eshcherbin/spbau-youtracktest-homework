package ru.spbau.eshcherbin.testing.youtracktest.elements.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

abstract public class BasePage {
  protected String address = "http://localhost:8080/";

  protected WebDriver driver;
  protected WebDriverWait wait;

  public BasePage(WebDriver driver, WebDriverWait wait) {
    this.driver = driver;
    this.wait = wait;
  }

  public void get() {
    driver.get(address);
    waitUntilPageLoaded();
  }

  protected abstract void waitUntilPageLoaded();
}
