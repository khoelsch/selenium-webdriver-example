package de.edgeway.uiproxy.pagemodel;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.opentest4j.TestAbortedException;

import java.time.Duration;


/**
 * A <a href="https://github.com/SeleniumHQ/selenium/wiki/PageObjects">PageObject</a> represents
 * either an entire web page or a fraction of the page the user can interact with via the browser.
 *
 * @author Kai Hoelscher
 * @since 0.0.1
 */
public abstract class PageObject {

  /**
   * The number of milliseconds to wait before polling for some DOM state again, e.g. when waiting
   * for the presence of an {@link org.openqa.selenium.WebElement element}.
   */
  private static final int DEFAULT_WAIT_POLLING_MILLIS = 200;

  /**
   * The number of seconds after which an exception is thrown, when waiting for some DOM state, e.g.
   * when waiting for the presence of an {@link org.openqa.selenium.WebElement element}.
   */
  private static final int DEFAULT_WAIT_TIMEOUT_SECONDS = 30;

  /**
   * Dafault value for the number of milliseconds to wait after clicking on a {@link WebElement}.
   */
  private static final int DEFAULT_CLICK_WAIT = 500;


  /**
   * See {@link #getDriver()}.
   */
  private WebDriver driver;


  /**
   * Creates a new {@link PageObject}, backed by the provided {@link WebDriver}.
   *
   * @param pageObjectType class toke of concrete sub-type of {@link PageObject} to instantiate
   * @param driver the {@link WebDriver} instance controlling the
   * @param <T> concrete sub-type of {@link PageObject} to instantiate
   * @return a new {@link PageObject}, backed by the provided {@link WebDriver}
   * @throws TestAbortedException if UI representation of created PageObject is not usable; see
   *     also {@link PageObject#selfVerify()}
   */
  public static <T extends PageObject> T createPageObject(Class<T> pageObjectType,
      WebDriver driver) {
    T pageObject = PageFactory.initElements(driver, pageObjectType);

    abortTestIfPageObjectNotUsable(pageObjectType, pageObject);
    pageObject.setDriver(driver);
    return pageObject;
  }


  /**
   * Creates a default {@link Wait} object that can be used to wait for a certain in the tests,
   * usually that an {@link org.openqa.selenium.WebElement element} is present in the DOM with
   * <pre>defaultWait().until(driver -&gt; driver.findElement(...);</pre>.
   *
   * @return a {@link Wait} object pre-configured for standard-DOM-polling
   */
  protected Wait<WebDriver> defaultWait() {
    return new FluentWait<>(getDriver())
        .pollingEvery(Duration.ofMillis(DEFAULT_WAIT_POLLING_MILLIS))
        .withTimeout(Duration.ofSeconds(DEFAULT_WAIT_TIMEOUT_SECONDS))
        .ignoring(NoSuchElementException.class);
  }

  /**
   * Stops test execution for a fixed amount of time after clicking on a {@link WebElement}.
   *
   * <p><strong>Use sparingly, since using this method often will make your test suite very slow
   * over time!</strong>.
   */
  protected void waitAfterClick() {
    waitFor(Duration.ofMillis(DEFAULT_CLICK_WAIT));
  }

  /**
   * A hard wait for a fixed amount of time.
   *
   * <p><strong>Use sparingly, since using this method often will make your test suite very slow
   * over time!</strong>.
   */
  private void waitFor(Duration duration) {
    try {
      Thread.sleep(duration.toMillis());
    } catch (InterruptedException shouldRarelyHappenException) {
      throw new RuntimeException(
          "An unexpected error occurred while waiting for sth. in the tests.");
    }
  }

  /**
   * Checks, that all mandatory elements of this {@link PageObject} are present and can be
   * interacted with (if that makes sense). If not, <strong>test execution is aborted!</strong>
   *
   * <p>This method will be called soon after the {@link PageObject} was instantiated to make sure,
   * that the test can proceed in a meaningful way: A concrete example would be a {@code LoginPage},
   * in which you would check for the presence of a username- and password-input-field, and, if both
   * elements are properly rendered, e.g. the input fields should not be disabled.
   *
   * <p>The idea is to fail early and to make clear that a precondition failed, prior to the test's
   * actual assertions. In other words: The actual assertions might not fail, but we couldn't reach
   * the validation point in the test's control flow, because something went wrong before.
   *
   * @throws org.opentest4j.TestAbortedException if this {@link PageObject} cannot be used in a
   *     meaningful way
   */
  protected abstract void selfVerify();

  /**
   * Aborts test execution, in case a certain condition is not truthy.
   *
   * @param condition the condition to check; an exception is thrown, in case condition
   *     evaluates to false
   * @param failureDescription a description of why test execution was aborted
   * @throws org.opentest4j.TestAbortedException if {@code condition} is false
   */
  protected void assumeThat(boolean condition, String failureDescription) {
    if (!condition) {
      throw new TestAbortedException(failureDescription);
    }
  }

  // make sure, the PageObject can be used (e.g. not disabled or hidden)
  private static <T extends PageObject> void abortTestIfPageObjectNotUsable(Class<T> pageObjectType,
      T pageObject) {

    try {
      pageObject.selfVerify();
    } catch (WebDriverException ex) {
      String message = String.format(
          "Test execution aborted, since PageObject \"%s\" doesn't seem to be rendered properly.",
          pageObjectType.getSimpleName());

      throw new TestAbortedException(message, ex);
    }
  }

  /**
   * Asserts that the {@link org.openqa.selenium.WebElement} is rendered in a way the user can
   * interact with it.
   *
   * @param element the {@link org.openqa.selenium.WebElement} to check
   * @return true, if the {@link org.openqa.selenium.WebElement} is rendered in a way the user can
   *     interact with it
   */
  protected boolean elementIsUsable(WebElement element) {
    return element.isDisplayed() && element.isEnabled();
  }

  /**
   * Asserts that the {@link org.openqa.selenium.WebElement} is visible to the user.
   *
   * @param element the {@link org.openqa.selenium.WebElement} to check
   * @return true, if the {@link org.openqa.selenium.WebElement} is visible to the user
   */
  protected boolean elementIsVisible(WebElement element) {
    return element.isDisplayed();
  }

  /**
   * Returns the {@link WebDriver} instance controlling the browser.
   *
   * @return the {@link WebDriver} instance controlling the browser
   */
  protected WebDriver getDriver() {
    return driver;
  }

  /**
   * Sets the {@link WebDriver} instance controlling the browser.
   *
   * @param driver the {@link WebDriver} to set
   */
  void setDriver(WebDriver driver) {
    this.driver = driver;
  }
}
