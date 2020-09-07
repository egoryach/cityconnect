package com.elliot.cityconnect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application with endpoint to determine if two cities are connected based on data from file source.
 * Cities may be directly or indirectly connected via connecting cities.
 * 
 * @author Elliot Goryachkovsky
 *
 */
@SpringBootApplication
public class CityConnectApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(CityConnectApplication.class, args);
	}

}
