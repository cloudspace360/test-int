package com.se.wp.library.utils.rest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import com.se.wp.library.utils.EnvironmentUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.se.wp.library.constants.Constants;

@Component
public class ConfigUtilRest {
	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigUtilRest.class);
	@Autowired
	EnvironmentUtil environmentUtil;
	private static final String PROPERTY_FILE_PATH= "property file path: {}";
	private String catalinaHome=System.getProperty("catalina.home");

	protected Map<String, String> readAllProperties(String languageCode, String fileName) throws IOException {
		Map<String, String> properties = null;
		String filePath = fetchPropertyFilePath(languageCode, fileName);
		LOGGER.debug(PROPERTY_FILE_PATH, filePath);
		if (filePath != null && !filePath.isEmpty()) {
			Properties prop = new Properties();
			FileInputStream inputStream = new FileInputStream(filePath);
			InputStreamReader reader = new InputStreamReader(inputStream,StandardCharsets.UTF_8);
			prop.load(reader);
			Enumeration<String> e = (Enumeration<String>) prop.propertyNames();
			properties = new TreeMap<>();
			String key;
			String value;
			while (e.hasMoreElements()) {
				key = e.nextElement();
				value = prop.getProperty(key);
				properties.put(key, value);
			}
			reader.close();
			inputStream.close();
		}
		return properties;
	}

	public String readProperty(String languageCode, String fileName, String propertyKey) throws IOException {
		String filePath = fetchPropertyFilePath(languageCode, fileName);
		LOGGER.debug(PROPERTY_FILE_PATH, filePath);
		String value = null;
		if (filePath != null && !filePath.isEmpty()) {
			Properties prop = new Properties();
			FileInputStream inputStream = new FileInputStream(filePath);
			InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
			prop.load(reader);
			value = prop.getProperty(propertyKey);
			reader.close();
			inputStream.close();
		}
		LOGGER.debug("single property for language: {} and key: {} = {}", languageCode, propertyKey, value);
		return value;
	}

	public String readProperty(String fileName, String propertyKey) throws IOException {
		String filePath = fetchPropertyFilePath(null, fileName);
		LOGGER.debug(PROPERTY_FILE_PATH, filePath);
		String value = null;
		if (filePath != null && !filePath.isEmpty()) {
			Properties prop = new Properties();
			FileInputStream inputStream = new FileInputStream(filePath);
			InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
			prop.load(reader);
			value = prop.getProperty(propertyKey);
			reader.close();
			inputStream.close();
		}
		LOGGER.debug("single property for key: {} = {}", propertyKey, value);
		return value;
	}

	public Map<String, String> readAllProperties(String fileName) throws IOException {
		Map<String, String> properties = null;
		String filePath = fetchPropertyFilePath(null, fileName);
		LOGGER.debug(PROPERTY_FILE_PATH, filePath);
		if (filePath != null && !filePath.isEmpty()) {
			Properties prop = new Properties();
			FileInputStream inputStream = new FileInputStream(filePath);
			InputStreamReader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
			prop.load(reader);
			Enumeration<String> e = (Enumeration<String>) prop.propertyNames();
			properties = new TreeMap<>();
			String key;
			String value;
			while (e.hasMoreElements()) {
				key = e.nextElement();
				value = prop.getProperty(key);
				properties.put(key, value);
			}
			reader.close();
			inputStream.close();
		}
		return properties;
	}

	private String fetchPropertyFilePath(String languageCode, String fileName) {
		String globalConfigLocation = environmentUtil.getGlobalConfigLocation();
		LOGGER.debug("globalConfigLocation1: {}", globalConfigLocation);
		if (globalConfigLocation == null || globalConfigLocation.isEmpty()) {
			LOGGER.debug("Getting config location using cataline home...");
			globalConfigLocation = catalinaHome + Constants.FILE_SEPERATOR
					+ Constants.GLOBAL_CONFIG_FOLDER_NAME;
		}
		LOGGER.debug("globalConfigLocation2: {}", globalConfigLocation);
		StringBuilder basePath = new StringBuilder().append(globalConfigLocation).append(Constants.FILE_SEPERATOR);
		LOGGER.debug("basePath: {}", basePath);
		String filePath = basePath + fileName;
		LOGGER.debug("filePath1: {}", filePath);
		if (languageCode != null && !languageCode.isEmpty()) {
			LOGGER.debug("Country specific properties...");
			LOGGER.debug("Get external country specific properties path...");
			filePath = basePath.append(Constants.GLOBAL_CONFIG_LANGUAGE_FOLDER_NAME).append(Constants.FILE_SEPERATOR).append(languageCode.toLowerCase()).append(Constants.FILE_SEPERATOR).append(fileName).toString();
		}
		LOGGER.debug("filePath2: {}", filePath);
		return filePath;
	}
}