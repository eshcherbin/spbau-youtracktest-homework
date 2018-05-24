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
}
