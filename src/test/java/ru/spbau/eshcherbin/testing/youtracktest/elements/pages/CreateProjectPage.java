package ru.spbau.eshcherbin.testing.youtracktest.elements.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CreateProjectPage extends BasePage {
  private static final String PROJECT_NAME_INPUT_ID = "id_l.C.EditProjectMain.projectName";
  private static final String SHORT_NAME_INPUT_ID = "id_l.C.EditProjectMain.shortName";
  private static final String PROJECT_LEAD_INPUT_ID = "id_l.C.EditProjectMain.projectLead";
  private static final String SAVE_PROJECT_BUTTON_ID = "id_l.C.EditProjectMain.saveProject";

  private WebElement projectNameInput;
  private WebElement shortNameInput;
  private WebElement projectLeadInput;
  private WebElement saveProjectButton;

  public CreateProjectPage(WebDriver driver, WebDriverWait wait) {
    super(driver, wait);
    address = address + "createProject";
  }

  public void createProject(String projectName, String shortName, String projectLead) {
    projectNameInput.sendKeys(projectName);
    shortNameInput.sendKeys(shortName);
    projectLeadInput.click();
    wait.until(ExpectedConditions.visibilityOfElementLocated(
        By.cssSelector("li[title='" + projectLead + " (" + projectLead + ")']")))
        .click();
    saveProjectButton.click();
  }

  @Override
  protected void waitUntilPageLoaded() {
    projectNameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(PROJECT_NAME_INPUT_ID)));
    shortNameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(SHORT_NAME_INPUT_ID)));
    projectLeadInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(PROJECT_LEAD_INPUT_ID)));
    saveProjectButton = wait.until(ExpectedConditions.elementToBeClickable(By.id(SAVE_PROJECT_BUTTON_ID)));
  }
}
