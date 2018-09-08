package de.edgeway.webdriver.utilities.assertions;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class WebElementAssertTest {

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