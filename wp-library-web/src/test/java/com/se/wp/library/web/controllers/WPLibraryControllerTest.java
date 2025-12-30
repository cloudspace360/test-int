package com.se.wp.library.web.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import com.schneider.oreo.service.document.DocumentPageBean.Documents;
import com.se.wp.library.constants.Constants;
import com.se.wp.library.models.DocumentListingDto;
import com.se.wp.library.models.DocumentListingResponseDto;
import com.se.wp.library.models.LocalizedData;
import com.se.wp.library.service.impl.DocumentListingService;
import com.se.wp.library.service.impl.LocalizationService;
import com.se.wp.library.utils.PropertiesUtil;
import com.se.wp.library.utils.WpLibraryUtil;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class WPLibraryControllerTest {
	
	@InjectMocks
	WPLibraryController wplLibraryController;
	
	@Mock
	DocumentListingService documentListingService;
	@Mock
	Model model;
	@Mock
	HttpServletRequest request;
	@Mock
	LocalizationService localizationService; 
	@Mock
	PropertiesUtil propertiesUtil;
	@Mock
	DocumentListingResponseDto documentListingResponseDto;
	@Mock
	Documents documents;
	@Mock
	DocumentListingDto documentListingDto;
	@Mock
	WpLibraryUtil wpLibraryUtil;
	
	private String languageCode = "en";
	private Map<String, String> requestFilters = new HashMap<>();
	String refNum="230";
	String docType="wp";
	String localCode="en";
	private LocalizedData localizedData;
	
	@BeforeEach
	public void setUp() throws Exception {
		requestFilters.put(Constants.DOC_TYPE, "12345");
		requestFilters.put(Constants.REF_NUM,"230");
		
		localizedData=new LocalizedData();
		Map<String, String> countries= new HashMap<>();
		countries.put("test", "test");
		String disclaimer="test";
		Map<String, String> footerLabels= new HashMap<>();
		footerLabels.put("test", "test");
		Map<String, String> labels= new HashMap<>();
		labels.put("test", "test");
		List<String> personas=new ArrayList<>();
		personas.add("test");
		
		localizedData.setCountries(countries);
		localizedData.setDisclaimer(disclaimer);
		localizedData.setFooterLabels(footerLabels);
		localizedData.setLabels(labels);
		localizedData.setPersonas(personas);
		
		Locale locale=new Locale("en", "GB");
		Mockito.when(wpLibraryUtil.getLocale()).thenReturn(locale);
		
		
		
	}
	
	@Test
	public void getWhitePaperDownloadLinksTest() {
		try {
			
			Mockito.when(localizationService.getLocalizedLabels(languageCode)).thenReturn(localizedData);
			Mockito.when(propertiesUtil.getProperty("document-types.properties", docType)).thenReturn("12345");
			wplLibraryController.getWhitePaperDownloadLinks(docType, refNum, languageCode, request, model);
		} catch (IOException e) {
			fail();
		}
	}
	
	@Test
	public void getWhitePaperDownloadLinksTest2() {
		try {
			
			Mockito.when(localizationService.getLocalizedLabels(languageCode)).thenReturn(localizedData);
			Mockito.when(wpLibraryUtil.getLanguage(request)).thenReturn("en");
			wplLibraryController.getWhitePaperDownloadLinks(null, refNum,null, request, model);
			wplLibraryController.getWhitePaperDownloadLinks("", refNum,null, request, model);
		} catch (IOException e) {
			fail();
		}
	}
	
	@Test
	public void getWhitePaperDownloadLinksTest3() {
		try {
			
			documentListingResponseDto = new DocumentListingResponseDto();
			List<DocumentListingDto> documents = new ArrayList<>();
			documentListingResponseDto.setDocuments(documents);
			documentListingDto=new DocumentListingDto();
			documentListingDto.setLanguageCodes("en");
			documents.add(documentListingDto);
			Mockito.when(localizationService.getLocalizedLabels(languageCode)).thenReturn(localizedData);
			Mockito.when(propertiesUtil.getProperty("document-types.properties", docType)).thenReturn("12345");
			Mockito.when(documentListingService.getDocuments(languageCode, requestFilters)).thenReturn(documentListingResponseDto);
			wplLibraryController.getWhitePaperDownloadLinks(docType, refNum,languageCode, request, model);
		} catch (IOException e) {
			fail();
		}
	}
	
	@Test
	public void getWhitePaperDownloadLinksWithLocaleTest() {
		try {
			wplLibraryController.getWhitePaperDownloadLinksWithLocale(docType, refNum, localCode, request, model);
		} catch (IOException e) {
			fail();
		}
	}

}
