package de.edgeway.foobarapp.browsertests.smoke;

import io.github.bonigarcia.SeleniumExtension;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

@ExtendWith(SeleniumExtension.class)
public class StartPageTest {

    public static final String WEBAPP_IP_ADDRESS = "http://192.168.99.100";
    public static final String WEBAPP_CONTEXT_PATH = "/tourguide";

    //
    //@DockerBrowser(type = BrowserType.CHROME, version = "latest") WebDriver driver
    //
    @Test
    public void should_display_userLogin(ChromeDriver chromeDriver) {
        chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        chromeDriver.navigate().to("http://google.com");

        WebElement searchBox = chromeDriver.findElement(By.tagName("input"));

        searchBox.clear();
        searchBox.sendKeys("FoooBar");
        searchBox.submit();

        Assertions.assertThat(chromeDriver.getTitle()).isEqualTo("Suck my boo!");
    }

//    @Test
    public void should_contain_toolbar_links() {


        Assertions.assertThat(true).isEqualTo(true);
    }
}