package de.edgeway.webdriver.utilities.assertions;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebElement;

/**
 * Custom {@link org.openqa.selenium.WebElement} assertions.
 */
public final class WebElementAssert extends AbstractAssert<WebElementAssert, WebElement> {
    public WebElementAssert(WebElement webElement, Class<?> selfType) {
        super(webElement, selfType);
    }

    public static WebElementAssert assertThat(WebElement webElement) {
        return new WebElementAssert(webElement, WebElementAssert.class);
    }

    /**
     * Asserts that the {@link org.openqa.selenium.WebElement} is rendered in a way the user can interact with it.
     *
     * @throws AssertionError if user cannot interact with {@link org.openqa.selenium.WebElement}
     */
    public WebElementAssert isUsable() {
        Assertions.assertThat(actual.isDisplayed()).isTrue();
        Assertions.assertThat(actual.isEnabled()).isTrue();
        return this;
    }
}
