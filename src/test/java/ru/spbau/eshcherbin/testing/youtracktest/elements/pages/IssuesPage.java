package ru.spbau.eshcherbin.testing.youtracktest.elements.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IssuesPage extends BasePage {
  public IssuesPage(WebDriver driver, WebDriverWait wait) {
    super(driver, wait);
    address = address + "issues";
  }

  @Override
  protected void waitUntilPageLoaded() {

  }
}
