package com.elliot.cityconnect.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.elliot.cityconnect.dao.CityConnectDAO;
import com.elliot.cityconnect.dao.FileCityConnectDAO;

@Configuration
@ComponentScan("com.elliot.cityconnect")
@PropertySource("classpath:application.properties")
public class TestConfig {
	 @Bean
	 public CityConnectDAO connectDAO() {
		 return new FileCityConnectDAO();
	 }
}

