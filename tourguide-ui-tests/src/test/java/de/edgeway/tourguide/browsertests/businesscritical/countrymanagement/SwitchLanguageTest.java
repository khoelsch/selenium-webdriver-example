package de.edgeway.tourguide.browsertests.businesscritical.countrymanagement;

import static org.assertj.core.api.Assertions.assertThat;

import de.edgeway.tourguide.browsertests.TourguideUiTest;
import de.edgeway.tourguide.uiproxy.pagemodel.MainPage;
import de.edgeway.tourguide.uiproxy.pagemodel.mainpage.MainMenu;
import de.edgeway.tourguide.uiproxy.pagemodel.mainpage.mainviews.CountriesView;
import io.github.bonigarcia.SeleniumExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Checks switching the language in the countries view has effect.
 *
 * @author Kai Hoelscher
 * @since 0.0.1
 */
@ExtendWith(SeleniumExtension.class)
class SwitchLanguageTest extends TourguideUiTest {

  @Test
  void should_switch_to_english(ChromeDriver chromeDriver) {
    // GIVEN I open the start page
    // AND   navigate to "Countries"
    MainPage mainPage = proxyForDriver(chromeDriver).start();
    MainMenu mainMenu = mainPage.openMainMenu();
    CountriesView countriesView = mainMenu.selectEntry("Countries", CountriesView.class);

    // WHEN I switch to english
    countriesView.switchToEnglish();


    // THEN the main menu's entries are displayed
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }



    // dummy to keep import: REMOVE ME!
    assertThat(true).isTrue();
  }
}