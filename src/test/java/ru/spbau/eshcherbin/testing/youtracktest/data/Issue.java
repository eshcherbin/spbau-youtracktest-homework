package ru.spbau.eshcherbin.testing.youtracktest.data;

public class Issue {
  private String summary;
  private String description;

  public Issue(String summary, String description) {
    this.summary = summary;
    this.description = description;
  }

  public String getSummary() {
    return summary;
  }

  public String getDescription() {
    return description;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Issue issue = (Issue) o;

    if (getSummary() != null ? !getSummary().equals(issue.getSummary()) : issue.getSummary() != null) return false;
    return getDescription() != null ? getDescription().equals(issue.getDescription()) : issue.getDescription() == null;
  }

  @Override
  public int hashCode() {
    int result = getSummary() != null ? getSummary().hashCode() : 0;
    result = 31 * result + (getDescription() != null ? getDescription().hashCode() : 0);
    return result;
  }
}
