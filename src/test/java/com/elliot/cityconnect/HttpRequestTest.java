package com.elliot.cityconnect;

import static org.assertj.core.api.BDDAssertions.then;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Test application with HTTP requests by starting the server.
 * 
 * @author Elliot Goryachkovsky
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HttpRequestTest {

	Logger logger = LoggerFactory.getLogger(HttpRequestTest.class);

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	public void shouldReturn200WhenSendingRequestToController() throws Exception {
		logger.info("Verifying status 200 when sending reqeust to controller.");
		
		ResponseEntity<String> entity = this.testRestTemplate.getForEntity(
				"http://localhost:" + this.port + "/connected?origin=new+york&destination=boston", String.class);

		then(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
	
	@Test
	public void shouldReturnYesBostonToPhiladelphia() throws Exception {
		logger.info("Verifying endpoint returns \"yes\" when cities indirectly connected: boston to philadelphia.");
		
		ResponseEntity<String> entity = this.testRestTemplate.getForEntity(
				"http://localhost:" + this.port + "/connected?origin=new+york&destination=boston", String.class);

		then(entity.getBody()).isEqualTo("yes");
	}

	@Test
	public void shouldReturnYesNewYorkToBoston() throws Exception {
		logger.info("Verifying endpoint returns \"yes\" when cities directly connected: new york to boston.");
		
		ResponseEntity<String> entity = this.testRestTemplate.getForEntity(
				"http://localhost:" + this.port + "/connected?origin=new+york&destination=boston", String.class);

		then(entity.getBody()).isEqualTo("yes");
	}
	
	@Test
	public void shouldReturnYesBostonToNewYork() throws Exception {
		logger.info("Verifying endpoint returns \"yes\" when cities directly connected: boston to new york.");
		
		ResponseEntity<String> entity = this.testRestTemplate.getForEntity(
				"http://localhost:" + this.port + "/connected?origin=boston&destination=new+york", String.class);

		then(entity.getBody()).isEqualTo("yes");
	}

	@Test
	public void shouldReturnNoIfCitiesNotConnected() throws Exception {
		logger.info("Verifying endpoint returns \"no\" when cities no connected.");
		
		ResponseEntity<String> entity = this.testRestTemplate.getForEntity(
				"http://localhost:" + this.port + "/connected?origin=new+york&destination=someCity", String.class);

		then(entity.getBody()).isEqualTo("no");
	}

	@Test
	public void shouldReturnNoIfOnlyOriginSupplied() throws Exception {
		logger.info("Verifying endpoint returns \"no\" when only origin supplied.");
		
		ResponseEntity<String> entity = this.testRestTemplate.getForEntity(
				"http://localhost:" + this.port + "/connected?origin=new+york&destination=", String.class);

		then(entity.getBody()).isEqualTo("no");
	}

	@Test
	public void shouldReturnNoIfOnlyDesitinationSupplied() throws Exception {
		logger.info("Verifying endpoint returns \"no\" when only destination supplied.");

		ResponseEntity<String> entity = this.testRestTemplate.getForEntity(
				"http://localhost:" + this.port + "/connected?origin=&destination=new+york", String.class);

		then(entity.getBody()).isEqualTo("no");
	}

}