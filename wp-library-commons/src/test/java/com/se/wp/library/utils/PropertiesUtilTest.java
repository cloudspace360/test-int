package com.se.wp.library.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.PropertySource;

import com.se.wp.library.constants.Constants;

import static org.junit.jupiter.api.Assertions.*;
@PropertySource("classpath:application.properties")
@ExtendWith(MockitoExtension.class)
public class PropertiesUtilTest   {
	private String countryCode = "US";
	private String languageCode = "en";
	
	@InjectMocks 
	PropertiesUtil propertiesUtil;
	
	@Mock
	ConfigUtil configUtil;
	@Mock
	EnvironmentUtil environmentUtil;

	
	@BeforeEach
	public void setUp() throws Exception {
		environmentUtil.setGlobalConfigLocation("C:\\Files_needed\\WebComponent\\ddc-api-conf\\");
	}
	
	@Test
	public void testGetPropertyTest1() {
		try {
			propertiesUtil.getProperty(languageCode, Constants.GLOBAL_CONFIG_FILE_NAME,"show.language.filter");
			propertiesUtil.getProperty("fr", Constants.LABELS_FILE_NAME, "prm-load-more-docs");
			
		} catch (IOException e) {
		}
	}
	

	@Test
	public void testGetPropertyTest2() {
		try {
			propertiesUtil.getProperty(languageCode, Constants.GLOBAL_CONFIG_FILE_NAME,"show.language.filter");
			propertiesUtil.getProperty("fr", Constants.LABELS_FILE_NAME, "prm-load-more-docs");
			
		} catch (IOException e) {
		}
	}
	
	
	@Test
	public void testGetAllPropertiesTest() {
		Map<String, String> propMap= new HashMap<>();
		propMap.put("key", "value");
		try {
			propertiesUtil.getAllProperties(languageCode, Constants.GLOBAL_CONFIG_FILE_NAME);
			Mockito.when(configUtil.readAllProperties(languageCode,  Constants.GLOBAL_CONFIG_FILE_NAME)).thenReturn(propMap);
			propertiesUtil.getAllProperties(languageCode, Constants.GLOBAL_CONFIG_FILE_NAME);
		} catch (IOException e) {
		}
	}
}
