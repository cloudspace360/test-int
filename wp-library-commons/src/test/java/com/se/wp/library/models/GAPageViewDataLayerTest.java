package com.se.wp.library.models;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class GAPageViewDataLayerTest {
	
	@InjectMocks
	GAPageViewDataLayer gaPageViewDataLayer;
	
	private static final String TEST="test";

	@Test
	public void test() {
		gaPageViewDataLayer.setArticleTitle(TEST);
		gaPageViewDataLayer.setBusinessUnit(TEST);
		gaPageViewDataLayer.setDigitalPlatform(TEST);
		gaPageViewDataLayer.setDigitalPlatformCountry(TEST);
		gaPageViewDataLayer.setEnvironment(TEST);
		gaPageViewDataLayer.setFromWebView(TEST);
		gaPageViewDataLayer.setPageCategory(TEST);
		gaPageViewDataLayer.setPageCategoryId(TEST);
		gaPageViewDataLayer.setPageLanguage(TEST);
		gaPageViewDataLayer.setPageProductId(TEST);
		gaPageViewDataLayer.setPageRangeId(TEST);
		gaPageViewDataLayer.setPageSubCategory(TEST);
		gaPageViewDataLayer.setPageSubCategoryId(TEST);
		gaPageViewDataLayer.setPageSubSubCategory(TEST);
		gaPageViewDataLayer.setPageTitle(TEST);
		gaPageViewDataLayer.setPageTopCategory(TEST);
		gaPageViewDataLayer.setTemplate(TEST);
		
		assertEquals(TEST,gaPageViewDataLayer.getArticleTitle());
		assertEquals(TEST,gaPageViewDataLayer.getBusinessUnit());
		assertEquals(TEST,gaPageViewDataLayer.getDigitalPlatform());
		assertEquals(TEST,gaPageViewDataLayer.getDigitalPlatformCountry());
		assertEquals(TEST,gaPageViewDataLayer.getEnvironment());
		assertEquals(TEST,gaPageViewDataLayer.getFromWebView());
		assertEquals(TEST,gaPageViewDataLayer.getPageCategory());
		assertEquals(TEST,gaPageViewDataLayer.getPageCategory());
		assertEquals(TEST,gaPageViewDataLayer.getPageCategoryId());
		assertEquals(TEST,gaPageViewDataLayer.getPageLanguage());
		assertEquals(TEST,gaPageViewDataLayer.getPageProductId());
		assertEquals(TEST,gaPageViewDataLayer.getPageRangeId());
		assertEquals(TEST,gaPageViewDataLayer.getPageSubCategory());
		assertEquals(TEST,gaPageViewDataLayer.getPageSubCategoryId());
		assertEquals(TEST,gaPageViewDataLayer.getPageTitle());
		assertEquals(TEST,gaPageViewDataLayer.getPageTopCategory());
		assertEquals(TEST,gaPageViewDataLayer.getTemplate());
		
		
	}
	
}
