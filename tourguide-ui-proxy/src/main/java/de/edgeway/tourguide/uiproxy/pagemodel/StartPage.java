package de.edgeway.tourguide.uiproxy.pagemodel;

import static org.openqa.selenium.support.How.CSS;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * The page that is displayed on application startup.
 *
 * @author Kai Hoelscher
 * @since 0.0.1
 */
public class StartPage {

  /**
   * See {@link #getProfileNameContainer()}.
   */
  @FindBy(how = CSS, using = CssSelectors.USER_PROFILE_NAME_CONTAINER)
  private WebElement profileNameContainer;

  @FindBy(how = CSS, using = CssSelectors.FOOTER)
  private WebElement footer;

  /**
   * Returns the page's footer.
   *
   * @return the page's footer
   */
  public WebElement getProfileNameContainer() {
    return profileNameContainer;
  }

  /**
   * Returns the Container (in the page header) that displays the name of the logged-in user.
   *
   * @return the Container (in the page header) that displays the name of the logged-in user
   */
  public WebElement getFooter() {
    return footer;
  }
}
