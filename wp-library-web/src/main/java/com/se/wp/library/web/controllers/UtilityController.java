package com.se.wp.library.web.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.se.wp.library.constants.Constants;
import com.se.wp.library.utils.EnvironmentUtil;
import com.se.wp.library.utils.PropertiesUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.sf.ehcache.CacheManager;

/**
 * 
 * @author Vikas.Kadam
 *
 */
@RestController
@RequestMapping("/api/internal")
@Api(value = "Utility controller")
public class UtilityController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UtilityController.class);
	@Autowired
	CacheManager myCacheManager;
	@Autowired
	PropertiesUtil propertiesUtil;
	@Value("${language.config.files}")
	private String languageConfigFiles;
	@Autowired
	EnvironmentUtil environmentUtil;
	
	private static final String CACHE_NAME="cacheName {}";

	@GetMapping("/clearAllCaches")
	@ApiOperation(value = "Clear all caches.")
	public String evictAllCache() {
		for (String cacheName : myCacheManager.getCacheNames()) {
			LOGGER.debug(CACHE_NAME, cacheName);
			myCacheManager.getCache(cacheName).flush();
		}
		return Constants.SUCCESS;
	}

	@GetMapping("/clearCongigurationCaches")
	@ApiOperation(value = "Clear all congigurations.")
	public String clearAllCongigurations() {
		for (String cacheName : myCacheManager.getCacheNames()) {
			LOGGER.debug(CACHE_NAME, cacheName);
			if (Constants.PROPERTIES_CACHE_NAME.equals(cacheName)
					|| Constants.LOCALIZED_LABELS_CACHE_NAME.equals(cacheName)) {
				myCacheManager.getCache(cacheName).flush();
				break;
			}
		}
		return Constants.SUCCESS;
	}

	@GetMapping("/clearDocumentsCache")
	@ApiOperation(value = "Clear documents cache.")
	public String clearDocumentsCache() {
		for (String cacheName : myCacheManager.getCacheNames()) {
			LOGGER.debug(CACHE_NAME, cacheName);
			if (Constants.DOCUMENT_LISTING_CACHE_NAME.equals(cacheName)) {
				myCacheManager.getCache(cacheName).flush();
				break;
			}
		}
		return Constants.SUCCESS;
	}

	@GetMapping("/getGlobalConfig")
	@ApiOperation(value = "Get global configuration")
	public ResponseEntity<Map<String, Map<String, String>>> getGlobalConfig() {
		HttpStatus status = HttpStatus.OK;
		Map<String, Map<String, String>> globalConfig = new HashMap<>();
		try {
			globalConfig.put(Constants.GLOBAL_CONFIG_FILE_NAME,
					propertiesUtil.getAllProperties(Constants.GLOBAL_CONFIG_FILE_NAME));
		} catch (IOException e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			LOGGER.error("Error getting globalConfig.", e);
		}
		return new ResponseEntity<>(globalConfig, status);
	}
	
	@GetMapping("/getGAConfig")
	@ApiOperation(value = "Get GA configuration")
	public ResponseEntity<Map<String, Map<String, String>>> getGAConfig() {
		HttpStatus status = HttpStatus.OK;
		Map<String, Map<String, String>> globalConfig = new HashMap<>();
		try {
			globalConfig.put(Constants.GA_PAGE_VIEW_EVENT_FILE_NAME,
					propertiesUtil.getAllProperties(Constants.GA_PAGE_VIEW_EVENT_FILE_NAME));
			globalConfig.put(Constants.GA_DOWNLOAD_EVENT_FILE_NAME,
					propertiesUtil.getAllProperties(Constants.GA_DOWNLOAD_EVENT_FILE_NAME));
		} catch (IOException e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			LOGGER.error("Error getting GA Config.", e);
		}
		return new ResponseEntity<>(globalConfig, status);
	}

	@GetMapping("{languageCode}/getLanguageConfig")
	@ApiOperation(value = "Get language configuration")
	public ResponseEntity<Map<String, Map<String, String>>> getLanguageConfig(
			@PathVariable("languageCode") String languageCode) {
		Map<String, Map<String, String>> languageConfig = new HashMap<>();
		HttpStatus status = HttpStatus.OK;
		Map<String, String> config;
		for (String fileName : languageConfigFiles.split(",")) {
			try {
				config = propertiesUtil.getAllProperties(languageCode.toLowerCase(), fileName);
				languageConfig.put(fileName, config);
			} catch (IOException e) {
				status = HttpStatus.INTERNAL_SERVER_ERROR;
				LOGGER.error("Error getting language config for {}-{}.", languageCode, fileName, e);
			}
		}
		return new ResponseEntity<>(languageConfig, status);
	}

	@GetMapping("/getLogLeval")
	@ApiOperation(value = "Get the log leval.")
	public String getLogLeval() {
		StringBuilder leval = new StringBuilder();
		if (LOGGER.isTraceEnabled()) {
			leval.append("TRACE,");
		}
		if (LOGGER.isDebugEnabled()) {
			leval.append("DEBUG,");
		}
		if (LOGGER.isInfoEnabled()) {
			leval.append("INFO,");
		}
		if (LOGGER.isWarnEnabled()) {
			leval.append("WARN,");
		}
		if (LOGGER.isErrorEnabled()) {
			leval.append("ERROR,");
		}
		return leval.toString();
	}

	@GetMapping("/getProfileInfo")
	@ApiOperation(value = "Get current environment profile info.")
	public String getProfileInfo() {
		String profile = null;
		String defaultFrofile = environmentUtil.getDefaultProfile();
		if (defaultFrofile != null && !defaultFrofile.isEmpty()) {
			profile = defaultFrofile;
		}
		String activeFrofile = environmentUtil.getActiveProfile();
		if (activeFrofile != null && !activeFrofile.isEmpty()) {
			profile = activeFrofile;
		}
		return profile;
	}
	
	@GetMapping(value = "/healthCheck/")
    public String healthCheck() {
        return "success";
    }
}