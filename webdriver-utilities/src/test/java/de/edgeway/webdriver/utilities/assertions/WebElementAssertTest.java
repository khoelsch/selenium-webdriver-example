package de.edgeway.webdriver.utilities.assertions;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class WebElementAssertTest {

    @Test
    void isUsable_accept_valid_WebElement() {
        WebElement validWebElement = mock(WebElement.class);

        when(validWebElement.isDisplayed()).thenReturn(true);
        when(validWebElement.isEnabled()).thenReturn(true);

        // Should not throw an exception
        WebElementAssert.assertThat(validWebElement).isUsable();
    }

    @Test
    void isUsable_throwIf_WebElementIsDisabled() {
        WebElement disabledWebElement = mock(WebElement.class);

        when(disabledWebElement.isDisplayed()).thenReturn(true);
        when(disabledWebElement.isEnabled()).thenReturn(false);

        assertThatExceptionOfType(AssertionError.class).isThrownBy( () ->
            WebElementAssert.assertThat(disabledWebElement).isUsable()
        );
    }

    @Test
    void isUsable_throwIf_WebElementIsNotDisplayed() {
        WebElement notShownWebElement = mock(WebElement.class);

        when(notShownWebElement.isEnabled()).thenReturn(true);
        when(notShownWebElement.isDisplayed()).thenReturn(false);

        assertThatExceptionOfType(AssertionError.class).isThrownBy( () ->
            WebElementAssert.assertThat(notShownWebElement).isUsable()
        );
    }

    @Test
    void containsText_accept_valid_WebElement() {
        WebElement validWebElement = mock(WebElement.class);

        when(validWebElement.getText()).thenReturn("foobar");

        // Should not throw an exception
        WebElementAssert.assertThat(validWebElement).containsText("foo");
        WebElementAssert.assertThat(validWebElement).containsText("bar");
        WebElementAssert.assertThat(validWebElement).containsText("foobar");
    }

    @Test
    void containsText_throwIf_text_is_null() {
        WebElement invalidWebElement = mock(WebElement.class);

        when(invalidWebElement.getText()).thenReturn(null);

        assertThatExceptionOfType(AssertionError.class).isThrownBy( () ->
            WebElementAssert.assertThat(invalidWebElement).containsText("foobar")
        );
    }

    @Test
    void containsText_throwIf_text_is_not_contained() {
        WebElement invalidWebElement = mock(WebElement.class);

        when(invalidWebElement.getText()).thenReturn("foobar");

        assertThatExceptionOfType(AssertionError.class).isThrownBy( () ->
            WebElementAssert.assertThat(invalidWebElement).containsText("foobarium")
        );
    }

    @Test
    void containsTextMatching_throwIf_text_is_not_contained() {
        WebElement webElement = mock(WebElement.class);

        when(webElement.getText()).thenReturn("foobar");

        assertThatExceptionOfType(AssertionError.class).isThrownBy( () ->
            WebElementAssert.assertThat(webElement).containsTextMatching("notasubstring")
        );
    }

    @Test
    void containsTextMatching_accept_matching_text() {
        WebElement webElement = mock(WebElement.class);

        when(webElement.getText()).thenReturn("foobar");

        WebElementAssert.assertThat(webElement).containsTextMatching("foobar");
        WebElementAssert.assertThat(webElement).containsTextMatching("foo...");
        WebElementAssert.assertThat(webElement).containsTextMatching("...bar");
        WebElementAssert.assertThat(webElement).containsTextMatching("f.+r");
    }


    //
    // --- Helper methods ----------------------------------------------------------------------------------------------
    //
}