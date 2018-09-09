package de.edgeway.tourguide.browsertests;

import de.edgeway.tourguide.uiproxy.TourguideProxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Base class for all Tourguide UI tests.
 *
 * @author Kai Hoelscher
 * @since 0.0.1
 */
public abstract class TourguideUiTest {

  //
  // DEVNOTE: Implement external configuration option, e.g. passing a system property
  //
  private static final String WEBAPP_IP_ADDRESS = "192.168.99.100";
  private static final String WEBAPP_PORT = "32769";
  private static final String WEBAPP_CONTEXT_PATH = "/tourguide";
  private static final String WEBAPP_START_URL =
      "http://" + WEBAPP_IP_ADDRESS + ":" + WEBAPP_PORT + "/" + WEBAPP_CONTEXT_PATH;

  /**
   * Creates a new {@link TourguideProxy}.
   *
   * @param chromeDriver the {@link WebDriver} instance backing the returned {@link TourguideProxy},
   * letting all the browser magic happen
   * @return a new {@link TourguideProxy}
   */
  protected TourguideProxy proxyForDriver(ChromeDriver chromeDriver) {
    return TourguideProxy.builder()
        .fromUrl(WEBAPP_START_URL)
        .withDriver(chromeDriver)
        .build();
  }
}