package com.se.wp.library.soap.bsl.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.se.wp.library.utils.EnvironmentUtil;
import com.se.wp.library.utils.PropertiesUtil;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class BslSoapRequestUtilTest  {
	private String countryCode = "US";
	private String languageCode = "en";

	@InjectMocks
	BslSoapRequestUtil bslSoapRequestUtil;

	@Mock
	PropertiesUtil propertiesUtil;
	@Mock
	EnvironmentUtil environmentUtil;

	@BeforeEach
	public void setUp() throws Exception {

	}

	@Test
	public void testGetDocumentServiceLocaleBeanTest1() {

		try {
			Mockito.when(environmentUtil.getBslLocale()).thenReturn("en_GB");
			Mockito.when(environmentUtil.getBslScopeBrand()).thenReturn("Schneider Electric");
			Mockito.when(environmentUtil.getBslScopeCountry()).thenReturn("WW");
			Mockito.when(environmentUtil.getBslScopeProject()).thenReturn("AllDocuments");
			com.schneider.oreo.service.document.LocaleBean localeBean = bslSoapRequestUtil.getDocumentServiceLocaleBean();
			assertEquals("GB", localeBean.getIsoCountry().toString());
			assertEquals("en", localeBean.getIsoLanguage().toString());
		} catch (Exception e) {
			fail();
		}

	}

}
