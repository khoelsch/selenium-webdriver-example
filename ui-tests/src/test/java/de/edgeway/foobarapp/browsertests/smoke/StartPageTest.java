package de.edgeway.foobarapp.browsertests.smoke;

import io.github.bonigarcia.SeleniumExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Checks the page that is rendered on application startup.
 */
@ExtendWith(SeleniumExtension.class)
class StartPageTest {


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
        assertThat(profileNameContainer.isDisplayed()).isTrue();
        assertThat(profileNameContainer.isEnabled()).isTrue();
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
        assertThat(footer.isDisplayed()).isTrue();
        assertThat(footer.isEnabled()).isTrue();
        assertThat(footer.getText()).contains("© 2018 Copyright - IWW Zentrum Wasser");
    }


    //
    // --- Helper methods ----------------------------------------------------------------------------------------------
    //

    private WebDriver openStartPage(ChromeDriver chromeDriver) {
        chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        chromeDriver.navigate().to(WEBAPP_START_URL);
        return chromeDriver;
    }
}