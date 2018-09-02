package de.edgeway.tourguide.browsertests.smoke;

import static de.edgeway.webdriver.utilities.assertions.WebElementAssert.assertThat;
import static java.time.Duration.ofMillis;
import static java.time.Duration.ofSeconds;

import de.edgeway.tourguide.uiproxy.TourguideProxy;
import de.edgeway.tourguide.uiproxy.pagemodel.StartPage;
import io.github.bonigarcia.SeleniumExtension;
import java.util.List;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

/**
 * Checks the page that is rendered on application startup.
 *
 * @author Kai Hoelscher
 * @since 0.0.1
 */
@ExtendWith(SeleniumExtension.class)
class StartPageTest {


  //
  // DEVNOTE: Implement external configuration option, e.g. passing a system property
  //
  private static final String WEBAPP_IP_ADDRESS = "192.168.99.100";
  private static final String WEBAPP_PORT = "32769";
  private static final String WEBAPP_CONTEXT_PATH = "/tourguide";
  private static final String WEBAPP_START_URL =
      "http://" + WEBAPP_IP_ADDRESS + ":" + WEBAPP_PORT + "/" + WEBAPP_CONTEXT_PATH;


  private TourguideProxy proxy;


  //
  //@DockerBrowser(type = BrowserType.CHROME, version = "latest") WebDriver driver
  //
  @Test
  void should_display_userLogin(ChromeDriver chromeDriver) {
    createProxy(chromeDriver);

    //
    // Given I open the start page
    //
    StartPage startPage = proxy.start();

    //
    // Then the name of the logged in user is displayed
    //
    assertThat(startPage.getProfileNameContainer()).isUsable();
    assertThat(startPage.getProfileNameContainer()).containsTextMatching("Hello, .+");
  }

  @Test
  void should_contain_copyright_notice(ChromeDriver chromeDriver) {
    createProxy(chromeDriver);

    //
    // Given I open the start page
    //
    StartPage startPage = proxy.start();

    //
    // Then a copyright notice is displayed in the footer
    //
    assertThat(startPage.getFooter()).isUsable();
    assertThat(startPage.getFooter()).containsText("© 2018 Copyright - IWW Zentrum Wasser");
  }

  @Test
  void should_display_main_menu_entries(ChromeDriver chromeDriver) {
    createProxy(chromeDriver);

    //
    // Given I open the start page
    //
    StartPage startPage = proxy.start();
    //
    // When I open the main menu
    //
    openMainMenu(chromeDriver);

    //
    // Then the main menu's entries are displayed
    //
    List<WebElement> menuEntries = chromeDriver
        .findElements(By.cssSelector("ul.navigation-menu a span"));
    WebElement firstEntry = menuEntries.get(0);
    assertThat(firstEntry).isUsable();
    assertThat(firstEntry).containsText("Countries");

    WebElement secondEntry = menuEntries.get(1);
    assertThat(secondEntry).isUsable();
    assertThat(secondEntry).containsText("Cities");

    WebElement thirdEntry = menuEntries.get(2);
    assertThat(thirdEntry).isUsable();
    assertThat(thirdEntry).containsText("Sights");
  }

  @Disabled("...because the wait in the openMainMenu must check for more conditions, e.g. all items")
  @Test
  void should_hide_main_menu(ChromeDriver chromeDriver) {
    createProxy(chromeDriver);

    //
    // Given I open the start page
    //
    StartPage startPage = proxy.start();

    //
    // Given I open the main menu
    //
    openMainMenu(chromeDriver);

    //
    // When I close the main menu
    //
    chromeDriver.findElement(By.cssSelector(".layout-submenu-title a.menu-button")).click();

    //
    // Then the main menu's entries are hidden
    //
// TODO implement check: What does it mean - in the CSS/Javascript -, that the menu items are hidden?
  }

  //
  // --- Helper methods ----------------------------------------------------------------------------------------------
  //

  private void createProxy(ChromeDriver chromeDriver) {
    proxy = TourguideProxy.builder()
        .fromUrl(WEBAPP_START_URL)
        .withDriver(chromeDriver)
        .build();
  }

  private void openMainMenu(ChromeDriver chromeDriver) {
    WebElement mainMenuButton = chromeDriver
        .findElement(By.cssSelector("ul.layout-tabmenu-nav a.tabmenuitem-link"));
    mainMenuButton.click();
    Wait<WebDriver> wait = new FluentWait<WebDriver>(chromeDriver)
        .pollingEvery(ofMillis(200))
        .withTimeout(ofSeconds(30))
        .ignoring(NoSuchElementException.class);
    wait.until(driver -> chromeDriver.findElement(By.cssSelector("ul.navigation-menu")));
  }
}