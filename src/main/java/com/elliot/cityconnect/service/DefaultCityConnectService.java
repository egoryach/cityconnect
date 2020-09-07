package com.elliot.cityconnect.service;

import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.elliot.cityconnect.dao.CityConnectDAO;

@Service
public class DefaultCityConnectService implements CityConnectService {
	Logger logger = LoggerFactory.getLogger(DefaultCityConnectService.class);

	@Autowired
	private CityConnectDAO connectDAO;

	@Override
	public boolean isCityConnected(String origin, String destination) {
		if (StringUtils.isEmpty(origin) || StringUtils.isEmpty(destination))
			return false;

		Set<String> returnedDestinations = connectDAO.getDestinationSet(origin.toUpperCase());
		if (returnedDestinations == null || returnedDestinations.isEmpty())
			return false;

		if (returnedDestinations.contains(destination.toUpperCase())) {
			logger.info("{} and {} are directly connected.", origin, destination);
			return true;
		} else {
			for (String city : returnedDestinations) {
				Set<String> cities = connectDAO.getDestinationSet(city);

				logger.info("checking connections for {}", cities);
				if (cities.contains(destination.toUpperCase())) {
					logger.info("{} and {} are connected by {}.", origin, destination, city);
					return true;
				}
			}
		}

		return false;
	}

}
