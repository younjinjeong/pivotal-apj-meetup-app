package io.pivotal.emcforum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by derrickwong on 29/9/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = RepositoryApplication.class)
@WebAppConfiguration
public class UserAnswerTests {

    @Autowired
    UserAnswerRepository userAnswerRepository;
    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
    }

    @Test
    public void isDrewTest() throws Exception{
        this.userAnswerRepository.deleteAll();
        UserAnswer ua = new UserAnswer("user@acme.com", "Peter", "a","a","a","a","a", false);
        this.userAnswerRepository.save(ua);

        this.mockMvc.perform(get("/isDrew"))
                .andExpect(status().isOk())
                .andExpect(content().string("false"))
                .andDo(print());

        this.userAnswerRepository.save(new UserAnswer("user2@acme.com", "Joe", "a","a","a","a","a", true));

        this.mockMvc.perform(get("/isDrew"))
                .andExpect(status().isOk())
                .andExpect(content().string("true"))
                .andDo(print());

    }
}
