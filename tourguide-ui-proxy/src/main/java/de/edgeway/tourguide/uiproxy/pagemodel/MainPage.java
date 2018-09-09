package de.edgeway.tourguide.uiproxy.pagemodel;

import static org.openqa.selenium.support.How.CSS;

import de.edgeway.uiproxy.pagemodel.PageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

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
   * Elements that represent the main menu entries.
   */
  @FindBy(how = CSS, using = CssSelectors.MAIN_MENU_ENTRIES)
  private List<WebElement> mainMenuEntries;


  @Override
  public void selfVerify() {
    assumeThat(elementIsUsable(profileNameContainer), "Profile name container should be usable.");
  }


  /**
   * Opens the main menu.
   */
  public void openMainMenu() {
    mainMenuButton.click();
    defaultWait().until(driver -> driver.findElement(By.cssSelector(CssSelectors.MAIN_MENU)));
  }

  /**
   * Returns the list of entries present in the main menu.
   *
   * @return the list of entries present in the main menu
   */
  public List<String> getMainMenuEntries() {
    return mainMenuEntries.stream()
        .map(WebElement::getText)
        .collect(Collectors.toList());
  }


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
}
