package com.se.wp.library.utils;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.se.wp.library.constants.Constants;

@Component
public class PropertiesUtil {
	@Autowired
	ConfigUtil configUtil;

	public Map<String, String> getAllProperties(String languageCode, String fileName) throws IOException {
		return configUtil.readAllProperties(languageCode, fileName);
	}

	public String getProperty(String languageCode, String fileName, String propertyKey) throws IOException {
		return configUtil.readProperty(languageCode, fileName, propertyKey);
	}

	public String getProperty(String fileName, String propertyKey) throws IOException {
		return configUtil.readProperty(fileName, propertyKey);
	}

	public Map<String, String> getAllProperties(String fileName) throws IOException {
		return configUtil.readAllProperties(fileName);
	}
}
