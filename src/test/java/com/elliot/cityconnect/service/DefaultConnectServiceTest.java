/**
 * 
 */
package com.elliot.cityconnect.service;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.elliot.cityconnect.dao.CityConnectDAO;

/**
 * Test CityConnect service with DAO stub.
 * 
 * @author Elliot Goryachkovsky
 *
 */
class DefaultConnectServiceTest {
	Logger logger = LoggerFactory.getLogger(DefaultConnectServiceTest.class);
	
	@InjectMocks
	private DefaultCityConnectService connectService; 

	@Mock
	private CityConnectDAO connectDAO;
	
	@BeforeEach
	public void initMocks() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
    public void testMockCreation(){
        assertNotNull(connectService);
        assertNotNull(connectDAO);
    }
	
	@Test
    public void testCnnectionExists(){
		String origin = "NEW YORK";
		String destination = "BOSTON";
		Set<String> return_destinations = new HashSet<>();
		return_destinations.add("BOSTON");

		// stub getDestination
		when(connectDAO.getDestinationSet(origin)).thenReturn(return_destinations);
		
		logger.info("Verifying isConnected returns true and DAO is called");
		assertTrue(connectService.isCityConnected(origin, destination));
		verify(connectDAO, times(1)).getDestinationSet(origin);		
    }
	
	@Test
    public void testConnectionDoesNotExist(){
		String origin = "NEW YORK";
		String destination = "BOSTON";
		Set<String> return_destinations = new HashSet<>();
		return_destinations.add("OTHER CITY");

		// stub getDestination
		when(connectDAO.getDestinationSet(origin)).thenReturn(return_destinations);
		
		logger.info("Verifying isConnected returns false and DAO is called.");
		assertFalse(connectService.isCityConnected(origin, destination));
		verify(connectDAO, times(1)).getDestinationSet(origin);		
    }
	
	@Test
    public void testOriginOnly(){
		String origin = "NEW YORK";
		String destination = "";
		Set<String> return_destinations = new HashSet<>();


		// stub getDestination
		when(connectDAO.getDestinationSet(origin)).thenReturn(return_destinations);
		
		logger.info("Verifying isConnected returns false and DAO not called when only origin supplied");
		assertFalse(connectService.isCityConnected(origin, destination));
		verify(connectDAO, times(0)).getDestinationSet(origin);		
    }

	@Test
    public void testDestinationOnly(){
		String origin = "";
		String destination = "BOSTON";
		Set<String> return_destinations = new HashSet<>();

		// stub getDestination
		when(connectDAO.getDestinationSet(origin)).thenReturn(return_destinations);
		
		logger.info("Verifying isConnected returns false and DAO not called when only destination supplied");
		assertFalse(connectService.isCityConnected(origin, destination));
		verify(connectDAO, times(0)).getDestinationSet(origin);		
    }
}
