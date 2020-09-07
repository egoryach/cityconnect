/**
 * 
 */
package com.elliot.cityconnect.dao;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.elliot.cityconnect.config.TestConfig;

/**
 * Unit test DAO.  Ensure DAO can load city-test.txt with 
 * 	1) emptly lines 
 *  2) whitespace around pairs
 *  3) incomplete pairs
 *  
 * @author Elliot Goryachkovsky
 *
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestConfig.class})
class FileConnectDAOTest {
	Logger logger = LoggerFactory.getLogger(FileConnectDAOTest.class);

	@Autowired
	private CityConnectDAO connectDAO;
	
	@Test
	void testMalformedCityFile() {
		String origin = "BOSTON";
		String destination = "NEW york";
		
		logger.info("Verifying DAO can filter out empty lines, whitespace and incomplete pairs");
		
		Set<String> destinations = connectDAO.getDestinationSet(origin);
		assertTrue(destinations.contains(destination.toUpperCase()));
	}
	
	@Test
	void testCityPairWithWhitespace() {
		String origin = "Trenton";
		String destination = "AlBany";
		
		logger.info("Verifying DAO will handle whitespace around city pairs");
		
		Set<String> destinations = connectDAO.getDestinationSet(origin);
		assertTrue(destinations.contains(destination.toUpperCase()));
	}
	
	@Test
	void tesBostonToNewYork() {
		String origin = "BOSTON";
		String destination = "NEW york";
		
		logger.info("Verifying Boston to New York exists");
		
		Set<String> destinations = connectDAO.getDestinationSet(origin);
		assertTrue(destinations.contains(destination.toUpperCase()));
	}
	
	@Test
	void tesNewYorkToBoston() {
		String origin = "NEW york";
		String destination = "BOSTON";
		
		logger.info("Verifying New York to Boston exists");
		
		Set<String> destinations = connectDAO.getDestinationSet(origin);
		assertTrue(destinations.contains(destination.toUpperCase()));
	}
}
