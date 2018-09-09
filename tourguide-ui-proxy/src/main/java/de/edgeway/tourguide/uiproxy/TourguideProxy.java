package de.edgeway.tourguide.uiproxy;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

import de.edgeway.tourguide.uiproxy.pagemodel.MainPage;
import de.edgeway.uiproxy.pagemodel.PageObject;
import org.openqa.selenium.WebDriver;

/**
 * A proxy of the web application that the tests operate on, instead of using WebDriver/Selenium
 * directly.
 *
 * <p>Think of a browser window in which the application-under-test is opened: Now, instead of
 * making low-level access with WebDriver APIs, you can easily navigate through the application by
 * calling methods on this proxy or its returned <a href="https://github.com/SeleniumHQ/selenium/wiki/PageObjects">PageObjects</a>.
 *
 * @author Kai Hoelscher
 * @since 0.0.1
 */
public class TourguideProxy {

  /**
   * The total amount of milliseconds to wait for the presence of a {@link
   * org.openqa.selenium.WebElement WebElement} in the DOM before throwing an exception, for example
   * when doing a click.
   */
  private static final int WEBELEMENT_ACCESS_TIMEOUT_MILLIS = 5000;


  /**
   * See {@link Builder#startUrl}.
   */
  private String startUrl;

  /**
   * See {@link Builder#withDriver(WebDriver)}.
   */
  private WebDriver driver;


  private TourguideProxy() {
    // prevent instantiation
  }

  /**
   * Returns the Tourguide application's start page.
   *
   * @return the Tourguide application's start page
   */
  public MainPage start() {
    driver.navigate().to(startUrl);

    return PageObject.createPageObject(MainPage.class, driver);
  }

  /**
   * Creates a fresh builder to configure a new TourguideProxy instance.
   *
   * @return a fresh builder to configure a new TourguideProxy instance
   */
  public static Builder builder() {
    return new Builder();
  }

  public static class Builder {

    private String startUrl = "http://google.com";
    private WebDriver driver = null;

    /**
     * Creates a new TourguideProxy instance according to configuration.
     *
     * @return a new TourguideProxy instance according to configuration
     */
    public TourguideProxy build() {
      TourguideProxy newInstance = new TourguideProxy();
      newInstance.startUrl = this.startUrl;
      newInstance.driver = this.driver;

      configureWebDriver();

      return newInstance;
    }

    /**
     * Sets start URL of the (web)application-under-test.
     *
     * @param startUrl the start URL of the (web)application-under-test
     * @return the current instance
     */
    public Builder fromUrl(String startUrl) {
      this.startUrl = startUrl;
      return this;
    }

    /**
     * Sets the {@link WebDriver} that is used to instrument the browser.
     *
     * @param driver the {@link WebDriver} that is used to instrument the browser
     * @return the current instance
     */
    public Builder withDriver(WebDriver driver) {
      this.driver = driver;
      return this;
    }

    private void configureWebDriver() {
      driver.manage().timeouts().implicitlyWait(WEBELEMENT_ACCESS_TIMEOUT_MILLIS, MILLISECONDS);
    }
  }
}
