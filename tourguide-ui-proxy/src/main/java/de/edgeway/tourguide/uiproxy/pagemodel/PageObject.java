package de.edgeway.tourguide.uiproxy.pagemodel;

import static java.time.Duration.ofMillis;
import static java.time.Duration.ofSeconds;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;


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
   * See {@link #getDriver()}.
   */
  private WebDriver driver;

  /**
   * Returns the {@link WebDriver} instance that controls the browser.
   *
   * @return the {@link WebDriver} instance that controls the browser
   */
  private WebDriver getDriver() {
    return driver;
  }

  /**
   * Set the {@link WebDriver} instance that controls the browser.
   *
   * @param driver the {@link WebDriver} to set
   */
  public void setDriver(WebDriver driver) {
    this.driver = driver;
  }

  /**
   * Creates a default {@link Wait} object that can be used to defaultWait for a certain conditions
   * in the tests, usually that an {@link org.openqa.selenium.WebElement element} is present in the
   * DOM with <pre>defaultWait().until(driver -> driver.findElement(...);</pre>.
   */
  Wait<WebDriver> defaultWait() {
    return new FluentWait<>(getDriver())
        .pollingEvery(ofMillis(DEFAULT_WAIT_POLLING_MILLIS))
        .withTimeout(ofSeconds(DEFAULT_WAIT_TIMEOUT_SECONDS))
        .ignoring(NoSuchElementException.class);
  }
}
