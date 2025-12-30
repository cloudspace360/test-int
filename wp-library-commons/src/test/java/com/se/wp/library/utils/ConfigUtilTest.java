package com.se.wp.library.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.se.wp.library.constants.Constants;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ConfigUtilTest  {
	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigUtilTest.class);
	private String languageCode = "en";
	@InjectMocks
	ConfigUtil configUtil;
	
	@Mock
	EnvironmentUtil environmentUtil;
   
	
	@BeforeEach
	public void setUp() throws Exception {
		environmentUtil.setGlobalConfigLocation("C:\\Schneider\\DDC\\wp-library");
		System.setProperty("catalina.home","C:\\Schneider\\DDC\\wp-library");
	}

	@Test
	public void testReadAllPropertiesTest() {
		try {
			configUtil.readAllProperties(languageCode, Constants.LABELS_FILE_NAME);
			configUtil.readAllProperties("document-types.properties");
		} catch (Exception e) {
			fail();
		}
	}
	
	@Test
	public void testReadPropertyTest() {
		try {
			configUtil.readProperty(languageCode, Constants.LABELS_FILE_NAME, "prm.wpl.no.document.found");
			configUtil.readProperty(Constants.GLOBAL_CONFIG_FILE_NAME, "exclude.doc.type");
		} catch (Exception e) {
			fail();
		}
	}

//	@Test
//	public void testGetPropertyFilePathTest() {
//		configUtil.getPropertyFilePath(languageCode, Constants.LABELS_FILE_NAME);
//	}

}
