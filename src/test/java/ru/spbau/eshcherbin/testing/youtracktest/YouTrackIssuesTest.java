package ru.spbau.eshcherbin.testing.youtracktest;

import static org.junit.Assert.*;

import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.spbau.eshcherbin.testing.youtracktest.data.Issue;
import ru.spbau.eshcherbin.testing.youtracktest.elements.pages.CreateProjectPage;
import ru.spbau.eshcherbin.testing.youtracktest.elements.pages.EditProjectPage;
import ru.spbau.eshcherbin.testing.youtracktest.elements.pages.IssuesPage;
import ru.spbau.eshcherbin.testing.youtracktest.elements.pages.LoginPage;

import java.util.List;

public class YouTrackIssuesTest {
  private static final int WAIT_TIMEOUT_SECONDS = 3;

  private static final String ROOT_LOGIN = "root";
  private static final String ROOT_PASSWORD = "root";
  private static final String TEST_PROJECT_NAME = "test";
  private static final String TEST_PROJECT_SHORT_NAME = "test";

  private static WebDriver driver;
  private static WebDriverWait wait;

  private IssuesPage issuesPage;

  @BeforeClass
  public static void configureDriverAndYouTrack() throws Exception {
    System.setProperty(
        "webdriver.gecko.driver",
        Thread.currentThread().getContextClassLoader().getResource("geckodriver").getPath()
    );

    driver = new FirefoxDriver();
    wait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);

    LoginPage loginPage = new LoginPage(driver, wait);
    loginPage.get();
    loginPage.logIn(ROOT_LOGIN, ROOT_PASSWORD);

    CreateProjectPage createProjectPage = new CreateProjectPage(driver, wait);
    createProjectPage.get();
    createProjectPage.createProject(TEST_PROJECT_NAME, TEST_PROJECT_SHORT_NAME, ROOT_LOGIN);
  }

  @Before
  public void setUp() throws Exception {
    issuesPage = new IssuesPage(driver, wait);
    issuesPage.get();
  }

  @Test
  public void test() throws Exception {
    Issue issue = new Issue("test", "test");
    issuesPage.createIssue(issue);

    List<Issue> issues = issuesPage.getIssues();
    assertTrue(issues.contains(issue));
  }

  @AfterClass
  public static void finish() throws Exception {
    EditProjectPage editProjectPage = new EditProjectPage(driver, wait, TEST_PROJECT_SHORT_NAME);
    editProjectPage.get();
    editProjectPage.deleteProject();

    driver.quit();
  }
}
