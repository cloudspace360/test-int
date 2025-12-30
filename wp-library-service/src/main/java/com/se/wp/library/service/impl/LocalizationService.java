package com.se.wp.library.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.se.wp.library.constants.Constants;
import com.se.wp.library.models.LocalizedData;
import com.se.wp.library.service.interfaces.ILocalizationService;
import com.se.wp.library.utils.PropertiesUtil;

@Service
public class LocalizationService implements ILocalizationService {
	@Autowired
	PropertiesUtil propertiesUtil;
	private static final Logger LOGGER = LoggerFactory.getLogger(LocalizationService.class);

	@Override
	@Cacheable(value = Constants.LOCALIZED_LABELS_CACHE_NAME)
	public LocalizedData getLocalizedLabels(String languageCode) {
		LocalizedData localizedData = new LocalizedData();
		localizedData.setLabels(getLabels(languageCode, Constants.LABELS_FILE_NAME));
		localizedData.setFooterLabels(getLabels(languageCode, Constants.FOOTER_LABELS_FILE_NAME));
		return localizedData;
	}

	private Map<String, String> getLabels(String languageCode, String fileName) {
		Map<String, String> englishLabels = new TreeMap<>();
		try {
			englishLabels = propertiesUtil.getAllProperties("en", fileName);
		} catch (Exception e1) {
			LOGGER.error("Error reading English labels");
		}
		LOGGER.debug("englishLabels: {}", englishLabels);
		Map<String, String> labels = englishLabels;
		if (!languageCode.equals("en")) {
			LOGGER.debug("Getting labels for non-english");
			try {
				labels = propertiesUtil.getAllProperties(languageCode, fileName);
			} catch (Exception e) {
				LOGGER.error("Error reading labels for {}", languageCode);
			}
		}
		LOGGER.debug("labels1: {}", labels);
		if (labels != null && !labels.isEmpty()) {
			englishLabels.putAll(labels);
			LOGGER.debug("englishLabels2 {}", englishLabels);
			labels = new HashMap<>();
			for (Entry<?, ?> entry : englishLabels.entrySet()) {
				labels.put(entry.getKey().toString().replace(".", "_"), entry.getValue().toString());
			}
			LOGGER.debug("labels2: {}", labels);
		}
		return labels;
	}
}