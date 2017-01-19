package io.pivotal.emcforum;

import io.pivotal.emcforum.controller.AnswerController;
import io.pivotal.emcforum.model.CorrectAnswer;
import io.pivotal.emcforum.model.DrawSize;
import io.pivotal.emcforum.service.CheckingService;
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

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = BackendApplication.class)
@WebAppConfiguration
public class BackendApplicationTests {


//	@Autowired
//	CorrectAnswer a;
	@Autowired
    CheckingService cs;
	@Autowired
	CorrectAnswer correctAnswer;
	@Autowired
    DrawSize drawSize;
	@Autowired
	private WebApplicationContext context;
	@Autowired
	AnswerController answerController;


	private MockMvc mockMvc;

	@Before
	public void setUp() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();
	}

	@Test
	public void test1(){

	}

}
