package de.edgeway.webdriver.utilities.assertions;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebElement;

/**
 * Custom {@link org.openqa.selenium.WebElement} assertions.
 *
 * @author Kai Hoelscher
 * @since 0.0.1
 */
public final class WebElementAssert extends AbstractAssert<WebElementAssert, WebElement> {

  @SuppressWarnings("WeakerAccess")
  public WebElementAssert(WebElement webElement, Class<?> selfType) {
    super(webElement, selfType);
  }

  public static WebElementAssert assertThat(WebElement webElement) {
    return new WebElementAssert(webElement, WebElementAssert.class);
  }

  /**
   * Asserts that the {@link org.openqa.selenium.WebElement}'s text content contains a certain
   * substring.
   *
   * @param expectedText the text context that should be present in {@link WebElement#getText()}
   * @return the current instance
   * @throws AssertionError if substring is not contained
   */
  @SuppressWarnings("UnusedReturnValue")
  public WebElementAssert containsText(String expectedText) {
    Assertions.assertThat(actual.getText())
        .describedAs("WebElement's text node content did not contain the expected text.")
        .contains(expectedText);
    return this;
  }

  /**
   * Asserts that the {@link org.openqa.selenium.WebElement}'s text matches a regular expression.
   *
   * @param regExPattern the {@link java.util.regex.Pattern regular expression pattern} {@link
   * WebElement#getText()} must match
   * @return the current instance
   * @throws AssertionError if text doesn't match
   */
  @SuppressWarnings("UnusedReturnValue")
  public WebElementAssert containsTextMatching(String regExPattern) {
    Assertions.assertThat(actual.getText())
        .describedAs("WebElement's text node content did not match RegEx.")
        .matches(regExPattern);
    return this;
  }
}
