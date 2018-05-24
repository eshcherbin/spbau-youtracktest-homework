package ru.spbau.eshcherbin.testing.youtracktest.elements.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.spbau.eshcherbin.testing.youtracktest.data.Issue;
import ru.spbau.eshcherbin.testing.youtracktest.elements.forms.CreateIssueForm;

import java.util.List;

public class IssuesPage extends BasePage {
  private static final String CREATE_ISSUE_LINK_TEXT = "Create Issue";
  private static final String ISSUES_LIST_ID = "id_l.I.c.il.issueList";

  private WebElement createIssueLink;

  public IssuesPage(WebDriver driver, WebDriverWait wait) {
    super(driver, wait);
    address = address + "issues";
  }

  public List<Issue> getIssues() {
    return null;
  }

  public void createIssue(Issue issue) {
    createIssueLink.click();
    CreateIssueForm createIssueForm = new CreateIssueForm(driver, wait);
    createIssueForm.locate();
    createIssueForm.submitIssue(issue);
    this.get();
  }

  @Override
  protected void waitUntilPageLoaded() {
    createIssueLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(CREATE_ISSUE_LINK_TEXT)));
  }
}
