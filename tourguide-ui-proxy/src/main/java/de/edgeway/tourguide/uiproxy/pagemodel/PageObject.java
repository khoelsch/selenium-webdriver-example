package de.edgeway.tourguide.uiproxy.pagemodel;

import org.openqa.selenium.WebDriver;

/**
 * A <a href="https://github.com/SeleniumHQ/selenium/wiki/PageObjects">PageObject</a> represents either
 * an entire web page or a fraction of the page the user can interact with via the browser.
 *
 * @author Kai Hoelscher
 * @since 0.0.1
 */
public abstract class PageObject {

  /**
   * See {@link #getDriver()}.
   */
  private WebDriver driver;

  /**
   * Returns the {@link WebDriver} instance that controls the browser.
   *
   * @return the {@link WebDriver} instance that controls the browser
   */
  WebDriver getDriver() {
    return driver;
  }

  public void setDriver(WebDriver driver) {
    this.driver = driver;
  }
}
