package ru.spbau.eshcherbin.testing.youtracktest.elements.pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Used here only to delete projects.
 */
public class EditProjectPage extends BasePage {
  private static final String DELETE_PROJECT_LINK_SELECTOR = "a[cn='l.E.deleteCurrentProject']";

  private WebElement deleteProjectLink;

  public EditProjectPage(WebDriver driver, WebDriverWait wait, String projectShortName) {
    super(driver, wait);
    address = address + "editProject/" + projectShortName;
  }

  public void deleteProject() {
    deleteProjectLink.click();
    Alert deletionConfirmationAlert = driver.switchTo().alert();
    deletionConfirmationAlert.accept();
  }

  @Override
  protected void waitUntilPageLoaded() {
    deleteProjectLink = wait.until(
        ExpectedConditions.elementToBeClickable(By.cssSelector(DELETE_PROJECT_LINK_SELECTOR)));
  }
}
