package ru.spbau.eshcherbin.testing.youtracktest.elements.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.spbau.eshcherbin.testing.youtracktest.data.Issue;
import ru.spbau.eshcherbin.testing.youtracktest.elements.forms.CreateIssueForm;

import java.util.List;
import java.util.stream.Collectors;

public class IssuesPage extends BasePage {
  private static final String CREATE_ISSUE_LINK_TEXT = "Create Issue";
  private static final String ISSUES_LIST_ID = "id_l.I.c.il.issueList";
  private static final String ISSUE_CONTAINER_SELECTOR = "div[cn='l.I.c.il.i.issueContainer']";
  private static final String ISSUE_EXPAND_LINK_SELECTOR = "a[cn='l.I.c.il.i.vi.collapse']";
  private static final String ISSUE_SUMMARY_CLASSNAME = "issue-summary";
  private static final String ISSUE_DESCRIPTION_CLASSNAME = "description";

  private WebElement createIssueLink;

  public IssuesPage(WebDriver driver, WebDriverWait wait) {
    super(driver, wait);
    address = address + "issues";
  }

  public List<Issue> getIssues() {
    WebElement issuesList = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(ISSUES_LIST_ID)));
    return issuesList.findElements(By.cssSelector(ISSUE_CONTAINER_SELECTOR)).stream()
        .map(container -> {
      container.findElement(By.cssSelector(ISSUE_EXPAND_LINK_SELECTOR)).click(); // expand issue to get description
      String summary = container.findElement(By.className(ISSUE_SUMMARY_CLASSNAME)).getText();
      String description = container.findElements(By.className(ISSUE_DESCRIPTION_CLASSNAME)).stream()
          .map(WebElement::getText).findAny().orElse("");
      return new Issue(summary, description);
    }).collect(Collectors.toList());
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
