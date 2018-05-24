package ru.spbau.eshcherbin.testing.youtracktest.elements.forms;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.spbau.eshcherbin.testing.youtracktest.data.Issue;

public class CreateIssueForm extends BaseForm {
  private static final String SUMMARY_TEXTAREA_ID = "id_l.I.ni.ei.eit.summary";
  private static final String DESCRIPTION_TEXTAREA_ID = "id_l.I.ni.ei.eit.description";
  private static final String SUBMIT_BUTTON_SELECTOR = "button[cn='l.I.ni.ei.submitButton']";

  private WebElement summaryTextarea;
  private WebElement descriptionTextarea;
  private WebElement submitButton;

  public CreateIssueForm(WebDriver driver, WebDriverWait wait) {
    super(driver, wait);
  }

  public void submitIssue(Issue issue) {
    summaryTextarea.sendKeys(issue.getSummary());
    descriptionTextarea.sendKeys(issue.getDescription());
    submitButton.click();
  }

  @Override
  public void locate() {
    summaryTextarea = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(SUMMARY_TEXTAREA_ID)));
    descriptionTextarea = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(DESCRIPTION_TEXTAREA_ID)));
    submitButton = wait.until(ExpectedConditions.visibilityOfElementLocated(
        By.cssSelector(SUBMIT_BUTTON_SELECTOR)));
  }
}
