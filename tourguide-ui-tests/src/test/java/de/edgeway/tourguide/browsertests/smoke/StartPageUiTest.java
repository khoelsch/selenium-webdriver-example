package de.edgeway.tourguide.browsertests.smoke;

import static de.edgeway.webdriver.utilities.assertions.WebElementAssert.assertThat;
import static org.assertj.core.api.Assertions.assertThat;

import de.edgeway.tourguide.browsertests.TourguideUiTest;
import de.edgeway.tourguide.uiproxy.pagemodel.MainPage;
import io.github.bonigarcia.SeleniumExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
    // GIVEN I open the start page
    MainPage mainPage = proxyForDriver(chromeDriver).start();

    // THEN the name of the logged in user is displayed
    assertThat(mainPage.getProfileNameContainer()).containsTextMatching("Hello, .+");
  }

  @Test
  void should_contain_copyright_notice(ChromeDriver chromeDriver) {
    // GIVEN I open the start page
    MainPage mainPage = proxyForDriver(chromeDriver).start();

    // THEN a copyright notice is displayed in the footer
    assertThat(mainPage.getFooter()).containsText("© 2018 Copyright - IWW Zentrum Wasser");
  }

  @Test
  void should_display_main_menu_entries(ChromeDriver chromeDriver) {
    // GIVEN I open the start page
    MainPage mainPage = proxyForDriver(chromeDriver).start();

    // WHEN I open the main menu
    mainPage.openMainMenu();

    // THEN the main menu's entries are displayed
    assertThat(mainPage.getMainMenuEntries()).containsExactly("Countries", "Cities", "Sights");
  }
}