package com.elliot.cityconnect.dao;

import java.util.Set;

public interface CityConnectDAO {	
	
	
	/**
	 * Returns a set of connected cities for given origin city.
	 * 
	 * @param origin city
	 * @return destination city
	 */
	public Set<String> getDestinationSet(String origin);
}
