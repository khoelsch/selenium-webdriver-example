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
}
