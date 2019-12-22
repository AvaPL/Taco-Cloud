package sia.tacocloud;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ScrController.class)
class ScrControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnOkStatusForScrGetRequest() throws Exception {
        mockMvc.perform(get("/scr")).andExpect(status().isOk());
    }

    @Test
    void scrPageViewShouldBeNamedScr() throws Exception {
        mockMvc.perform(get("/scr")).andExpect(view().name("scr"));
    }

    @Test
    void scrPageShouldContainSCRString() throws Exception {
        mockMvc.perform(get("/scr")).andExpect(content().string(containsString("SCR")));
    }
}