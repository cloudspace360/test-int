package com.se.wp.library.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.se.wp.library.business.impl.DocumentListingBusiness;
import com.se.wp.library.constants.Constants;
import com.se.wp.library.models.DocumentListingResponseDto;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class DocumentListingServiceTest {
	
	@InjectMocks
	DocumentListingService documentListingService;
	@Mock
	DocumentListingBusiness documentListingBusiness;
	@Mock
	DocumentListingResponseDto documentListingResponseDto;
	
	private String languageCode = "en";
	private Map<String, String> requestFilters = new HashMap<>();
	
	@BeforeEach
	public void setUp() throws Exception {
		requestFilters.put(Constants.DOC_TYPE, "12345");
		requestFilters.put(Constants.REF_NUM,"230");
	}

	@Test
	public void getDocumentsTest() {
		try {
			Mockito.when(documentListingBusiness.getDocuments(languageCode, requestFilters)).thenReturn(documentListingResponseDto);
			documentListingService.getDocuments(languageCode, requestFilters);
		} catch (Exception e) {
			fail();
		}
	}
}
