package ru.spbau.eshcherbin.testing.youtracktest.elements.forms;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BaseForm {
  protected WebDriver driver;
  protected WebDriverWait wait;

  public BaseForm(WebDriver driver, WebDriverWait wait) {
    this.driver = driver;
    this.wait = wait;
  }

  public abstract void locate();
}
