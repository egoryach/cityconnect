package com.elliot.cityconnect;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.elliot.cityconnect.controller.CityConnectController;
import com.elliot.cityconnect.service.CityConnectService;

/**
 * Test CityConnectController controller with mock service.
 * 
 * @author Elliot Goryachkovskky
 */
@WebMvcTest(CityConnectController.class)
public class MockConnectControllerTest {
	Logger logger = LoggerFactory.getLogger(MockConnectControllerTest.class);
			
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CityConnectService connectService;

	@Test
	public void shouldReturnYesIfCitiesConnected() throws Exception {
		String origin = "boston";
		String destination = "newark";
		
		logger.info("Testing Controller returns yes when connection exists");
		when(connectService.isCityConnected(origin, destination)).thenReturn(true);
		
		this.mockMvc.perform(get("/connected?origin=boston&destination=newark")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("yes")));
	}
	
	@Test
	public void shouldReturnNoIfCitiesNotConnected() throws Exception {
		String origin = "boston";
		String destination = "othercity";
		
		logger.info("Testing Controller returns no when no connection exists.");
		when(connectService.isCityConnected(origin, destination)).thenReturn(false);
		
		this.mockMvc.perform(get("/connected?origin=bostonk&destination=othercity")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("no")));
	}
}
