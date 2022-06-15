package com.springboottest.helloworld;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloWorldApplicationTests {

	private MockMvc mockMvc;


	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(new HelloWorldApplication()).build();
	}

	@Test
	public void testHello() throws Exception{

		mockMvc.perform(MockMvcRequestBuilders.get("/hello")
		.accept(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.content().string(Matchers.equalTo("hello world")));

	}

}
