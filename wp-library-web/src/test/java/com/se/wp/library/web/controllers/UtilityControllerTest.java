package com.se.wp.library.web.controllers;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.cache.Cache;



import com.se.wp.library.constants.Constants;
import com.se.wp.library.utils.EnvironmentUtil;
import com.se.wp.library.utils.PropertiesUtil;

import static org.junit.jupiter.api.Assertions.*;
import net.sf.ehcache.CacheManager;
@ExtendWith(MockitoExtension.class)
public class UtilityControllerTest {

	@InjectMocks
	UtilityController utilityController;
	@Mock
	CacheManager cacheManager;
	@Mock
	PropertiesUtil propertiesUtil;
	@Mock
	EnvironmentUtil environmentUtil;
	@Mock
	Cache cache;
	
	List<String> cacheList = new  ArrayList<>();
	
	@BeforeEach
	public void setUp() throws Exception {
		cacheList.add("propertiesCache");
		cacheList.add("localizationCache");
		cacheList.add("documentListing");
//		Mockito.when(cacheManager.getCacheNames()).thenReturn(cacheList);
//		Mockito.when(cacheManager.getCache(ArgumentMatchers.anyString())).thenReturn(cache);
	}
	@Test
	public void testEvictAllCacheTest()
	{
		try
	{
		assertEquals(Constants.SUCCESS,utilityController.evictAllCache());
	}
		catch(Exception e){
			
		}
	}

	@Test
	public void testGetLogLevalTest()
	{
		utilityController.getLogLeval();
	}
	
	@Test
	public void clearAllCongigurationsTest() {
		utilityController.clearAllCongigurations();
	}
	
	@Test
	public void clearDocumentsCacheTest() {
		utilityController.clearDocumentsCache();
	}
	
	@Test
	public void getGlobalConfigTest(){
		utilityController.getGlobalConfig();
	}
	
/*	@Test
	public void getLanguageConfigTest(){
		utilityController.getLanguageConfig("en");
	}*/
	
	@Test
	public void getProfileInfoTest(){
		Mockito.when(environmentUtil.getDefaultProfile()).thenReturn("PROD");
		Mockito.when(environmentUtil.getActiveProfile()).thenReturn("PROD");
		utilityController.getProfileInfo();
	}
	
}
