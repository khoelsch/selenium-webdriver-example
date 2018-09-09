package de.edgeway.tourguide.uiproxy.pagemodel.mainpage.mainviews;

import static org.openqa.selenium.support.How.CSS;
import static org.openqa.selenium.support.How.XPATH;

import de.edgeway.tourguide.uiproxy.CssSelectors;
import de.edgeway.tourguide.uiproxy.XPaths;
import de.edgeway.uiproxy.pagemodel.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * View for Country management.
 *
 * @author Kai Hoelscher
 * @since 0.0.1
 */
public class CountriesView extends PageObject {

  /**
   * The view's header.
   */
  @FindBy(how = CSS, using = CssSelectors.COUNTRIES_VIEW_HEADER)
  private WebElement header;

  /**
   * Elements that represent the main menu entries.
   */
  @FindBy(how = XPATH, using = XPaths.COUNTRIES_VIEW_LANGUAGE_BUTTON_ENGLISH)
  private WebElement buttonLanguageEnglish;

  /**
   * The table holding the data of the countries.
   */
  @FindBy(how = CSS, using = CssSelectors.COUNTRIES_VIEW_TABLE)
  private WebElement tableCountries;



  @Override
  public void selfVerify() {
    assumeThat(elementIsVisible(header), "Countries view's header not visible.");
    assumeThat(elementIsVisible(tableCountries), "Table with countries not visible.");
  }


  /**
   * Switches the UI to english language.
   */
  public void switchToEnglish() {
    buttonLanguageEnglish.click();
    waitAfterClick();
  }

  /**
   * Clicks on an entry in the main menu.
   *
   * @param labelName the name of the menu entry to click
   */
  /*public void selectEntry(String labelName) {
    mainMenuEntries.stream()
        .filter(elem -> elem.getText().equals(labelName))
        .findFirst()
        .get()
        .click();
  }*/
}
