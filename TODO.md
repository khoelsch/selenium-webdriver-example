# Open TODOs =

0. Implement test: Open Countries, check that switching language works
0. Extract MainMenu-PageObject from MainPage.java: mainMenu.open(), etc.
0. Implement test: Open Countries, check that a new record can be added
0. Write unit tests... among others: TourguideProxy and Tourguide.Builder (e.g. Preconditions.checkArgument => webdriver must not be null, URL maynot be empty and must be a parsable URI)
0. Implement MainMenuPage.close() //=> chromeDriver.findElement(By.cssSelector(".layout-submenu-title a.menu-button")).click()
0. Provide Maven profile for executing headless tests
0. (Fix checkstyle violations in TEST code, except underscore stuff)
0. Generate code coverage information in Maven build
0. Separate smoke tests from the rest (use Junit 5's @Tag annotation?)
0. Execute tests in parallel
0. Use Java properties to handle localization(?)