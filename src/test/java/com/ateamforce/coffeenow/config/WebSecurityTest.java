package com.ateamforce.coffeenow.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


/**
 *
 * @author Sakel
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class WebSecurityTest {
 
    @Autowired
    private MockMvc mockMvc;
 
    @Test
    public void testIfRegularHomePageIsSecured() throws Exception {
        final ResultActions resultActions = mockMvc.perform(get("/regular/home"));
        resultActions
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("http://localhost/regular/login"));
    }
 
    @Test
    @WithMockUser(username="admin",roles={"USER","ADMIN"})
    public void testIfLoggedUserHasAccessToRegularHomePage() throws Exception {
        final ResultActions resultActions = mockMvc.perform(get("/regular/home"));
        resultActions
                .andExpect(status().isOk())
                .andExpect(view().name("regular/home"));
    }
 
    @Test
    @WithMockUser(username="admin",roles={"USER","ADMIN"})
    public void testIfLoggedUserHasAccessToSpecialHomePage() throws Exception {
        final ResultActions resultActions = mockMvc.perform(get("/special/home"));
        resultActions
                .andExpect(status().isOk())
                .andExpect(view().name("special/home"));
    }
 
}
