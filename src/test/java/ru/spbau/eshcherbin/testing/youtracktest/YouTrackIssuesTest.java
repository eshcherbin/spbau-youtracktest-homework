package ru.spbau.eshcherbin.testing.youtracktest;

import static java.util.Collections.singletonList;
import static org.junit.Assert.*;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.thedeanda.lorem.Lorem;
import com.thedeanda.lorem.LoremIpsum;
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
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class YouTrackIssuesTest {
  private static final int WAIT_TIMEOUT_SECONDS = 3;

  private static final String ROOT_LOGIN = "root";
  private static final String ROOT_PASSWORD = "root";
  private static final String TEST_PROJECT_NAME = "test";
  private static final String TEST_PROJECT_SHORT_NAME = "test";

  private static WebDriver driver;
  private static WebDriverWait wait;
  private static Lorem lorem = LoremIpsum.getInstance();

  private IssuesPage issuesPage;

  @BeforeClass
  public static void configureDriverAndLogIn() {
    System.setProperty(
        "webdriver.gecko.driver",
        Thread.currentThread().getContextClassLoader().getResource("geckodriver").getPath()
    );

    driver = new FirefoxDriver();
    wait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);

    LoginPage loginPage = new LoginPage(driver, wait);
    loginPage.get();
    loginPage.logIn(ROOT_LOGIN, ROOT_PASSWORD);
  }

  @Before
  public void setUp() {
    CreateProjectPage createProjectPage = new CreateProjectPage(driver, wait);
    createProjectPage.get();
    createProjectPage.createProject(TEST_PROJECT_NAME, TEST_PROJECT_SHORT_NAME, ROOT_LOGIN);

    issuesPage = new IssuesPage(driver, wait);
    issuesPage.get();
  }

  @Test
  public void testOneIssue() {
    Issue issue = new Issue("test", "test");
    issuesPage.createIssue(issue);

    assertEquals(singletonList(issue), issuesPage.getIssues());
  }

  @Test
  public void testMultipleIssues() {
    List<Issue> expectedIssues = IntStream.range(0, 5)
        .mapToObj(i -> new Issue("test" + i, "test" + i))
        .collect(Collectors.toList());
    for (Issue issue : Lists.reverse(expectedIssues)) {
      issuesPage.createIssue(issue);
    }

    assertEquals(expectedIssues, issuesPage.getIssues());
  }

  @Test
  public void testNoDescription() {
    Issue issue = new Issue("test", "");
    issuesPage.createIssue(issue);

    assertEquals(singletonList(issue), issuesPage.getIssues());
  }

  @Test
  public void testLongSummary() {
    Issue issue = new Issue(lorem.getWords(50), "test");
    System.out.println(issue.getSummary());
    issuesPage.createIssue(issue);

    assertEquals(singletonList(issue.getShortenedVersion()), issuesPage.getIssues());
  }

  @Test
  public void testLongDescription() {
    Issue issue = new Issue("test", lorem.getWords(50));
    issuesPage.createIssue(issue);

    assertEquals(singletonList(issue), issuesPage.getIssues());
  }

  @Test
  public void testLongSummaryAndDescription() {
    Issue issue = new Issue(lorem.getWords(50), lorem.getWords(50));
    issuesPage.createIssue(issue);

    assertEquals(singletonList(issue.getShortenedVersion()), issuesPage.getIssues());
  }

  @Test
  public void testSpacesInLongSummary() {
    Issue issue = new Issue("test" + Strings.repeat(" ", 200) + "test", "test");
    issuesPage.createIssue(issue);

    assertEquals(singletonList(issue.getShortenedVersion()), issuesPage.getIssues());
  }

  @Test
  public void testSpacesInLongDescription() {
    Issue issue = new Issue("test", "test" + Strings.repeat(" ", 200) + "test");
    issuesPage.createIssue(issue);

    assertEquals(singletonList(issue), issuesPage.getIssues());
  }

  @Test
  public void testUncommonLanguagesInShortSummary() {
    List<Issue> expectedIssues = ImmutableList.of(
        new Issue("Тест", "Russian"),
        new Issue("测试", "Chinese"),
        new Issue("اختبار", "Arabic"),
        new Issue("გამოცდა", "Georgian")
    );
    for (Issue issue : Lists.reverse(expectedIssues)) {
      issuesPage.createIssue(issue);
    }

    assertEquals(expectedIssues, issuesPage.getIssues());
  }

  @Test
  public void testUncommonLanguagesInShortDescription() {
    List<Issue> expectedIssues = ImmutableList.of(
        new Issue("Russian", "Тест"),
        new Issue("Chinese", "测试"),
        new Issue("Arabic", "اختبار"),
        new Issue("Georgian", "გამოცდა")
    );
    for (Issue issue : Lists.reverse(expectedIssues)) {
      issuesPage.createIssue(issue);
    }

    assertEquals(expectedIssues, issuesPage.getIssues());
  }

  @Test
  public void testUncommonLanguagesInLongSummary() {
    List<Issue> expectedIssues = ImmutableList.of(
        new Issue(Strings.repeat("Тест ", 100), "Russian"),
        new Issue(Strings.repeat("测试 ", 100), "Chinese"),
        new Issue(Strings.repeat("اختبار", 100), "Arabic"),
        new Issue(Strings.repeat("გამოცდა", 100), "Georgian")
    );
    for (Issue issue : Lists.reverse(expectedIssues)) {
      issuesPage.createIssue(issue);
    }

    assertEquals(expectedIssues.stream().map(Issue::getShortenedVersion).collect(Collectors.toList()),
        issuesPage.getIssues());
  }

  @Test
  public void testUncommonLanguagesInLongDescription() {
    List<Issue> expectedIssues = ImmutableList.of(
        new Issue("Russian", Strings.repeat("Тест", 100)),
        new Issue("Chinese", Strings.repeat("测试", 100)),
        new Issue("Arabic", Strings.repeat("اختبار", 100)),
        new Issue("Georgian", Strings.repeat("გამოცდა", 100))
    );
    for (Issue issue : Lists.reverse(expectedIssues)) {
      issuesPage.createIssue(issue);
    }

    assertEquals(expectedIssues, issuesPage.getIssues());
  }

  @After
  public void tearDown() {
    EditProjectPage editProjectPage = new EditProjectPage(driver, wait, TEST_PROJECT_SHORT_NAME);
    editProjectPage.get();
    editProjectPage.deleteProject();
  }

  @AfterClass
  public static void finish() {
    driver.quit();
  }
}
