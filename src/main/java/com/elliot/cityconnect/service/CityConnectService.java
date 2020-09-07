package com.elliot.cityconnect.service;

public interface CityConnectService {
	
	/**
	 * Checks whether two cities are connected.
	 * 
	 * @param origin
	 * @param destination
	 * @return true if cities are connected
	 */
	public boolean isCityConnected(String origin, String destination);
	
}
