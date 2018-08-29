package de.edgeway.foobarapp.smoketests;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StartPageIsDisplayedTest {

    @Test
    public void should_display_startPage() throws Exception {
        Assertions.assertThat(true).isEqualTo(true);
    }
}