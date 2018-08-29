package de.edgeway.foobarapp.uitests.smoke;

import org.assertj.core.api.Assertions;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StartPageTest {

    public static final String WEBAPP_IP_ADDRESS = "http://192.168.99.100";
    public static final String WEBAPP_CONTEXT_PATH = "/tourguide";

    @Test
    public void should_display_userLogin() {


        Assertions.assertThat(true).isEqualTo(true);
    }

//    @Test
    public void should_contain_toolbar_links() {


        Assertions.assertThat(true).isEqualTo(true);
    }
}