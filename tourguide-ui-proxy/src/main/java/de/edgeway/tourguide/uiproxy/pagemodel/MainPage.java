package de.edgeway.tourguide.uiproxy.pagemodel;

import static java.time.Duration.ofMillis;
import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.support.How.CSS;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

/**
 * The page that is displayed on application startup.
 *
 * @author Kai Hoelscher
 * @since 0.0.1
 */
public class MainPage extends PageObject {

  /**
   * See {@link #getProfileNameContainer()}.
   */
  @FindBy(how = CSS, using = CssSelectors.USER_PROFILE_NAME_CONTAINER)
  private WebElement profileNameContainer;

  /**
   * See {@link #getFooter()}.
   */
  @FindBy(how = CSS, using = CssSelectors.FOOTER)
  private WebElement footer;

  /**
   * Button that opens the main menu, when clicked.
   */
  @FindBy(how = CSS, using = CssSelectors.MAIN_MENU_BUTTON)
  private WebElement mainMenuButton;


  /**
   * Returns the Container (in the page header) that displays the name of the logged-in user.
   *
   * @return the Container (in the page header) that displays the name of the logged-in user
   */
  public WebElement getProfileNameContainer() {
    return profileNameContainer;
  }

  /**
   * Returns the page's footer.
   *
   * @return the page's footer
   */
  public WebElement getFooter() {
    return footer;
  }

  /**
   * Opens the main menu.
   */
  public void openMainMenu() {
    mainMenuButton.click();
    Wait<WebDriver> wait = new FluentWait<>(getDriver())
        .pollingEvery(ofMillis(200))
        .withTimeout(ofSeconds(30))
        .ignoring(NoSuchElementException.class);
    wait.until(driver -> driver.findElement(By.cssSelector(CssSelectors.MAIN_MENU)));
  }
}
