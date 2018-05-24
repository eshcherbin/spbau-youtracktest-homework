package ru.spbau.eshcherbin.testing.youtracktest.elements.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {
  private static final String LOGIN_INPUT_ID = "id_l.L.login";
  private static final String PASSWORD_INPUT_ID = "id_l.L.password";
  private static final String LOGIN_BUTTON_ID = "id_l.L.loginButton";

  private WebElement loginInput;
  private WebElement passwordInput;
  private WebElement loginButton;

  public LoginPage(WebDriver driver, WebDriverWait wait) {
    super(driver, wait);
    address = address + "login";
  }

  public void logIn(String login, String password) {
    loginInput.sendKeys(login);
    passwordInput.sendKeys(password);
    loginButton.click();
  }

  @Override
  protected void waitUntilPageLoaded() {
    loginInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(LOGIN_INPUT_ID)));
    passwordInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(PASSWORD_INPUT_ID)));
    loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.id(LOGIN_BUTTON_ID)));
  }
}
