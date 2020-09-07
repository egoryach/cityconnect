package com.elliot.cityconnect;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

/**
 * Do not start the server and test only the layer below. 
 * 
 * @author Elliot Goryachkovsky
 */
@SpringBootTest
@AutoConfigureMockMvc
public class MockWebAppTest {
	Logger logger = LoggerFactory.getLogger(MockWebAppTest.class);
			
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void shouldReturnDefaultMessage() throws Exception {
		logger.info("Verifying controller is working with default message of no ");
		
		this.mockMvc.perform(get("/connected?origin=new+york&destination=boston")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("no")));
	}
}
