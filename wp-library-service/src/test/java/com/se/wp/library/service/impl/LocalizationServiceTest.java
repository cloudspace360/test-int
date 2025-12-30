package com.se.wp.library.service.impl;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.se.wp.library.utils.PropertiesUtil;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class LocalizationServiceTest {

	@InjectMocks
	LocalizationService localizationService;
	@Mock
	PropertiesUtil propertiesUtil;

	@Test
	public void getLocalizedLabelsTest() {
		String languageCode="en";
		localizationService.getLocalizedLabels(languageCode);
		Map<String, String> englishLabels = new TreeMap<>();
		try {
			Mockito.when(propertiesUtil.getAllProperties(ArgumentMatchers.anyString(),ArgumentMatchers.anyString())).thenReturn(englishLabels);
		} catch (IOException e) {
			fail();
		}
		localizationService.getLocalizedLabels(languageCode);
	}
	
	@Test
	public void getLocalizedLabelsTest2() {
		String languageCode="fr";
		Map<String, String> englishLabels = new TreeMap<>();
		englishLabels.put("test.key", "testValue");
		try {
			Mockito.when(propertiesUtil.getAllProperties(ArgumentMatchers.anyString(),ArgumentMatchers.anyString())).thenReturn(englishLabels);
		} catch (IOException e) {
			fail();
		}
		localizationService.getLocalizedLabels(languageCode);
	}
	
}