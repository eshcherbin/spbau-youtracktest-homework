package ru.spbau.eshcherbin.testing.youtracktest.data;

import com.google.common.base.MoreObjects;

import java.util.Objects;

public class Issue {
  private static final int SHORT_SUMMARY_LENGTH_LIMIT = 200;

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

  public String getShortenedSummary() {
    return summary.length() <= SHORT_SUMMARY_LENGTH_LIMIT ?
        summary :
        summary.substring(0, SHORT_SUMMARY_LENGTH_LIMIT).trim();
  }

  public Issue getShortenedVersion() {
    return new Issue(getShortenedSummary(), getDescription());
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add("summary", summary)
        .add("description", description)
        .toString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Issue issue = (Issue) o;
    return Objects.equals(getSummary(), issue.getSummary()) &&
        Objects.equals(getDescription(), issue.getDescription());
  }

  @Override
  public int hashCode() {

    return Objects.hash(getSummary(), getDescription());
  }
}
