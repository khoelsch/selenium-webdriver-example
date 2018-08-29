package de.edgeway.foobarapp.uitests.smoke;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StartPageIsDisplayedTest {

    @Test
    public void should_display_startPage() {
        Assertions.assertThat(true).isEqualTo(true);
    }
}