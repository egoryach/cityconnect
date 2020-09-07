/**
 * 
 */
package com.elliot.cityconnect.dao;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Loads city pairs from file.
 * 
 * @author Elliot Goryachkovsky
 *
 */
@Component
public class FileCityConnectDAO implements CityConnectDAO {
	Logger logger = LoggerFactory.getLogger(FileCityConnectDAO.class);

	@Value(value = "${city.filename}")
	private String cityFilename;

	private Map<String, Set<String>> mapping = new HashMap<>();

	@Override
	public Set<String> getDestinationSet(String origin) {
		return mapping.get(origin.toUpperCase());
	}

	/**
	 * Load city pairs from file. Filter out whitespace, empty lines and incomplete
	 * pairs.
	 */
	@PostConstruct
	private void init() {
		// load up from file
		try (Stream<String> stream = Files
				.lines(Paths.get(getClass().getClassLoader().getResource(cityFilename).toURI()))) {

			stream.filter(line -> !line.isEmpty()).filter(line -> line.contains(","))
					.map(line -> line.trim().split("\\s*,\\s*")).forEach(line -> {
						if (line.length < 2 || line[0].isEmpty() || line[1].isEmpty())
							return;
						String a = line[0].toUpperCase();
						String b = line[1].toUpperCase();
						mapping.putIfAbsent(a, new HashSet<String>());
						mapping.putIfAbsent(b, new HashSet<String>());
						mapping.get(a).add(b);
						mapping.get(b).add(a);
					});
			mapping = Collections.unmodifiableMap(mapping);
		} catch (Exception e) {
			logger.error("Unable to load data from file {}", cityFilename);
		}
	}
}
