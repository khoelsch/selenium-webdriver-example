package de.edgeway.tourguide.browsertests.smoke;

import static de.edgeway.webdriver.utilities.assertions.WebElementAssert.assertThat;

import de.edgeway.tourguide.uiproxy.pagemodel.MainPage;
import io.github.bonigarcia.SeleniumExtension;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Checks the page that is rendered on application startup.
 *
 * @author Kai Hoelscher
 * @since 0.0.1
 */
@ExtendWith(SeleniumExtension.class)
class StartPageUiTest extends TourguideUiTest {

  //
  //@DockerBrowser(type = BrowserType.CHROME, version = "latest") WebDriver driver
  //
  @Test
  void should_display_userLogin(ChromeDriver chromeDriver) {
    //
    // Given I open the start page
    //
    MainPage mainPage = proxyForDriver(chromeDriver).start();

    //
    // Then the name of the logged in user is displayed
    //
    assertThat(mainPage.getProfileNameContainer()).isUsable();
    assertThat(mainPage.getProfileNameContainer()).containsTextMatching("Hello, .+");
  }

  @Test
  void should_contain_copyright_notice(ChromeDriver chromeDriver) {
    //
    // Given I open the start page
    //
    MainPage mainPage = proxyForDriver(chromeDriver).start();

    //
    // Then a copyright notice is displayed in the footer
    //
    assertThat(mainPage.getFooter()).isUsable();
    assertThat(mainPage.getFooter()).containsText("© 2018 Copyright - IWW Zentrum Wasser");
  }

  @Test
  void should_display_main_menu_entries(ChromeDriver chromeDriver) {
    //
    // Given I open the start page
    //
    MainPage mainPage = proxyForDriver(chromeDriver).start();

    //
    // When I open the main menu
    //
    mainPage.openMainMenu();

    //
    // Then the main menu's entries are displayed
    //
/*

TODO continue >>>>>>>>

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
*/
  }

  @Disabled("...because the wait in the openMainMenu must check for more conditions, e.g. all items")
  @Test
  void should_hide_main_menu(ChromeDriver chromeDriver) {
    //
    // Given I open the start page
    //
    MainPage mainPage = proxyForDriver(chromeDriver).start();

    //
    // Given I open the main menu
    //
    mainPage.openMainMenu();

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
}