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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import org.springframework.security.test.context.support.WithMockUser;


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
        final ResultActions resultActions = mockMvc.perform(get("/"));
        resultActions
                .andExpect(status().isOk());
    }
 
    @Test
    @WithMockUser(username="admin",roles={"client","admin"})
    public void testIfLoggedUserHasAccessToRegularHomePage() throws Exception {
        final ResultActions resultActions = mockMvc.perform(get("/administrator/dashboard"));
        resultActions
                .andExpect(status().isOk())
                .andExpect(view().name("administrator/dashboard"));
    }
 
    @Test
    @WithMockUser(username="admin",roles={"user","admin"})
    public void testIfLoggedUserHasAccessToSpecialHomePage() throws Exception {
        final ResultActions resultActions = mockMvc.perform(get("/store/dashboard"));
        resultActions
                .andExpect(status().isOk())
                .andExpect(view().name("store/dashboard"));
    }
 
}
