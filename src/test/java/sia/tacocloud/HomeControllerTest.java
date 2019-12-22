package sia.tacocloud;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(HomeController.class)
class HomeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnOkStatusForRootGetRequest() throws Exception {
        mockMvc.perform(get("/")).andExpect(status().isOk());
    }

    @Test
    void homePageViewShouldBeNamedHome() throws Exception {
        mockMvc.perform(get("/")).andExpect(view().name("home"));
    }

    @Test
    void homePageShouldContainWelcomeString() throws Exception {
        mockMvc.perform(get("/")).andExpect(content().string(containsString("Welcome to...")));
    }
}