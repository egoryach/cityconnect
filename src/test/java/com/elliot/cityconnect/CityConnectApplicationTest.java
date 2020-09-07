package com.elliot.cityconnect;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest	
class CityConnectApplicationTest {
	Logger logger = LoggerFactory.getLogger(CityConnectApplicationTest.class);
			
	@Test
	void contextLoads() {
		logger.info("verifying context is loaded.");
	}

}
