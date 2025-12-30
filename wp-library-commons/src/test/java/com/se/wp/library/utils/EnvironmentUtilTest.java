package com.se.wp.library.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class EnvironmentUtilTest  {
	
	@InjectMocks
	EnvironmentUtil environmentUtil;
	
	private static final String TEST="TestValue";
	@Test
	public void testEnvironmentUtilTest() {
		
		environmentUtil.setArchiveDownloadUrlTemplate(TEST);
		environmentUtil.setDocumentImageUrlTemplate(TEST);
		environmentUtil.setNonArchiveDownloadUrlTemplate(TEST);
		environmentUtil.setGlobalConfigLocation(TEST);
		environmentUtil.setDefaultProfile("default");
		environmentUtil.setGlobalConfigLocation(TEST);
		environmentUtil.setPublicBslServiceUrl(TEST);
		environmentUtil.setBslLocale(TEST);
		environmentUtil.setBslVersion(TEST);
		environmentUtil.setBslScopeBrand(TEST);
		environmentUtil.setBslScopeCountry(TEST);
		environmentUtil.setBslScopeProject(TEST);
		environmentUtil.setActiveProfile(TEST);
		
		assertTrue(TEST.equalsIgnoreCase(environmentUtil.getArchiveDownloadUrlTemplate()));
		assertTrue(TEST.equalsIgnoreCase(environmentUtil.getDocumentImageUrlTemplate()));
		assertTrue(TEST.equalsIgnoreCase(environmentUtil.getNonArchiveDownloadUrlTemplate()));
		assertTrue(TEST.equalsIgnoreCase(environmentUtil.getGlobalConfigLocation()));
		assertEquals("default",environmentUtil.getDefaultProfile());
		assertEquals(TEST,environmentUtil.getGlobalConfigLocation());
		assertEquals(TEST,environmentUtil.getPublicBslServiceUrl());
		assertEquals(TEST,environmentUtil.getBslLocale());
		assertEquals(TEST,environmentUtil.getBslVersion());
		assertEquals(TEST,environmentUtil.getBslScopeBrand());
		assertEquals(TEST,environmentUtil.getBslScopeCountry());
		assertEquals(TEST,environmentUtil.getBslScopeProject());
		assertEquals(TEST,environmentUtil.getActiveProfile());
		
		
		
		
		
	}

}
