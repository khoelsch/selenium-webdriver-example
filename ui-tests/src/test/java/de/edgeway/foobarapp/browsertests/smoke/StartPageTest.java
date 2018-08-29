package de.edgeway.foobarapp.browsertests.smoke;

import io.github.bonigarcia.SeleniumExtension;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Checks the page that is rendered on application startup.
 */
@ExtendWith(SeleniumExtension.class)
public class StartPageTest {

    private static final String WEBAPP_IP_ADDRESS = "192.168.99.100";
    private static final String WEBAPP_PORT = "32769";
    private static final String WEBAPP_CONTEXT_PATH = "/tourguide";
    private static final String WEBAPP_START_URL = "http://" + WEBAPP_IP_ADDRESS + ":" + WEBAPP_PORT + "/" + WEBAPP_CONTEXT_PATH;

    //
    //@DockerBrowser(type = BrowserType.CHROME, version = "latest") WebDriver driver
    //
    @Test
    public void should_display_userLogin(ChromeDriver chromeDriver) {
        //
        // Given I open the start page
        //
        chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        chromeDriver.navigate().to(WEBAPP_START_URL);

        //
        // Then the name of the logged in user is displayed
        //
        WebElement profileNameContainer = chromeDriver.findElement(By.className("profile-name"));
        assertThat(profileNameContainer.isDisplayed()).isTrue();
        assertThat(profileNameContainer.isEnabled()).isTrue();
        assertThat(profileNameContainer.getText()).matches("Hello, .+");
    }

//    @Test
    public void should_contain_toolbar_links() {


        assertThat(true).isEqualTo(true);
    }
}