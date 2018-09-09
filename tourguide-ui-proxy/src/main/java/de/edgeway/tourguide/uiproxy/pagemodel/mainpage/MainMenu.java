package de.edgeway.tourguide.uiproxy.pagemodel.mainpage;

import static org.openqa.selenium.support.How.CSS;

import de.edgeway.tourguide.uiproxy.CssSelectors;
import de.edgeway.uiproxy.pagemodel.PageObject;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The main menu. Allows navigating between the main views of the Tourguide application.
 *
 * @author Kai Hoelscher
 * @since 0.0.1
 */
public class MainMenu extends PageObject {

  /**
   * Parent container of main menu.
   */
  @FindBy(how = CSS, using = CssSelectors.MAIN_MENU)
  private WebElement mainMenuParent;

  /**
   * Elements that represent the main menu entries.
   */
  @FindBy(how = CSS, using = CssSelectors.MAIN_MENU_ENTRIES)
  private List<WebElement> mainMenuEntries;


  @Override
  public void selfVerify() {
    assumeThat(elementIsUsable(mainMenuParent), "Main menu container should be usable.");
  }


  /**
   * Returns the list of entries present in the main menu.
   *
   * @return the list of entries present in the main menu
   */
  public List<String> entries() {
    return mainMenuEntries.stream()
        .map(WebElement::getText)
        .collect(Collectors.toList());
  }

  /**
   * Clicks on an entry in the main menu.
   *
   * @param labelName the name of the menu entry to click
   * @param viewType the type of view to instantiate
   * @param <T> the type of view to instantiate, for example
   *     {@link de.edgeway.tourguide.uiproxy.pagemodel.mainpage.mainviews.CountriesView}
   * @return {@link PageObject} representing the opened view
   */
  public <T extends PageObject> T selectEntry(String labelName, Class<T> viewType) {
    mainMenuEntries.stream()
        .filter(elem -> elem.getText().equals(labelName))
        .findFirst()
        .get()
        .click();

    return createPageObject(viewType, getDriver());
  }
}
