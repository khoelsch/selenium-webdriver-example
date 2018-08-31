package de.edgeway.foobarapp.browsertests.smoke;

import io.github.bonigarcia.SeleniumExtension;
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

import java.util.List;

import static de.edgeway.webdriver.utilities.assertions.WebElementAssert.assertThat;
import static java.time.Duration.ofMillis;
import static java.time.Duration.ofSeconds;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Checks the page that is rendered on application startup.
 */
@ExtendWith(SeleniumExtension.class)
class StartPageTest {


    //
    // DEVNOTE: Implement external configuration option, e.g. passing a system property
    //
    private static final String WEBAPP_IP_ADDRESS = "192.168.99.100";
    private static final String WEBAPP_PORT = "32769";
    private static final String WEBAPP_CONTEXT_PATH = "/tourguide";
    private static final String WEBAPP_START_URL = "http://" + WEBAPP_IP_ADDRESS + ":" + WEBAPP_PORT + "/" + WEBAPP_CONTEXT_PATH;


    //
    //@DockerBrowser(type = BrowserType.CHROME, version = "latest") WebDriver driver
    //
    @Test
    void should_display_userLogin(ChromeDriver chromeDriver) {
        //
        // Given I open the start page
        //
        openStartPage(chromeDriver);

        //
        // Then the name of the logged in user is displayed
        //
        WebElement profileNameContainer = chromeDriver.findElement(By.className("profile-name"));

        assertThat(profileNameContainer).isUsable();
        assertThat(profileNameContainer.getText()).matches("Hello, .+");
    }

    @Test
    void should_contain_copyright_notice(ChromeDriver chromeDriver) {
        //
        // Given I open the start page
        //
        openStartPage(chromeDriver);

        //
        // Then a copyright notice is displayed in the footer
        //
        WebElement footer = chromeDriver.findElement(By.className("footer-content"));
        assertThat(footer).isUsable();
        assertThat(footer.getText()).contains("© 2018 Copyright - IWW Zentrum Wasser");
    }

    @Test
    void should_display_main_menu_entries(ChromeDriver chromeDriver) {
        //
        // Given I open the start page
        //
        openStartPage(chromeDriver);

        //
        // When I open the main menu
        //
        openMainMenu(chromeDriver);

        //
        // Then the main menu's entries are displayed
        //
        List<WebElement> menuEntries = chromeDriver.findElements(By.cssSelector("ul.navigation-menu a span"));
// TODO impl de.edgeway.webdriver.utilities.assertions.WebElementAssert.java...
        assertThat(menuEntries.get(0).getText()).isEqualTo("Countries");
        assertThat(menuEntries.get(0)).isUsable();

        assertThat(menuEntries.get(1).getText()).isEqualTo("Cities");
        assertThat(menuEntries.get(1)).isUsable();

        assertThat(menuEntries.get(2).getText()).isEqualTo("Sights");
        assertThat(menuEntries.get(2)).isUsable();
    }

    @Disabled("...because the wait in the openMainMenu must check for more conditions, e.g. all items")
    @Test
    void should_hide_main_menu(ChromeDriver chromeDriver) {
        //
        // Given I open the start page
        //
        openStartPage(chromeDriver);

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

    private WebDriver openStartPage(ChromeDriver chromeDriver) {
        chromeDriver.manage().timeouts().implicitlyWait(10, SECONDS);
        chromeDriver.navigate().to(WEBAPP_START_URL);
        return chromeDriver;
    }

    private void openMainMenu(ChromeDriver chromeDriver) {
        WebElement mainMenuButton = chromeDriver.findElement(By.cssSelector("ul.layout-tabmenu-nav a.tabmenuitem-link"));
        mainMenuButton.click();
        Wait<WebDriver> wait = new FluentWait<WebDriver>(chromeDriver)
                .pollingEvery(ofMillis(200))
                .withTimeout(ofSeconds(30))
                .ignoring(NoSuchElementException.class);
        wait.until(driver -> chromeDriver.findElement(By.cssSelector("ul.navigation-menu")));
    }
}