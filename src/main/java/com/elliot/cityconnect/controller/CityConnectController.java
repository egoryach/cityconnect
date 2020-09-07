package com.elliot.cityconnect.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elliot.cityconnect.service.CityConnectService;

@RestController
public class CityConnectController {
	Logger logger = LoggerFactory.getLogger(CityConnectController.class);
	
	@Autowired
	CityConnectService connectService;

	@GetMapping("/connected")
	public String isConnected(
			@RequestParam(name = "origin", required = true) String origin, 
			@RequestParam(name = "destination", required = true) String destination) 
	{
		logger.info("Checking if origin {} and destination {} are connected.", origin, destination);
		
		return connectService.isCityConnected(origin, destination) ? "yes" : "no";
	}
}


