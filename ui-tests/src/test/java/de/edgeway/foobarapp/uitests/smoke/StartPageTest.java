package de.edgeway.foobarapp.uitests.smoke;

import io.github.bonigarcia.SeleniumExtension;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.chrome.ChromeDriver;

@ExtendWith(SeleniumExtension.class)
public class StartPageTest {

    public static final String WEBAPP_IP_ADDRESS = "http://192.168.99.100";
    public static final String WEBAPP_CONTEXT_PATH = "/tourguide";

    //
    //@DockerBrowser(type = BrowserType.CHROME, version = "latest") WebDriver driver
    //
    @Test
    public void should_display_userLogin(ChromeDriver chromeDriver) {
        chromeDriver.get("http://google.com");

        Assertions.assertThat(chromeDriver.getTitle()).isEqualTo("Suck my boo!");
    }

//    @Test
    public void should_contain_toolbar_links() {


        Assertions.assertThat(true).isEqualTo(true);
    }
}