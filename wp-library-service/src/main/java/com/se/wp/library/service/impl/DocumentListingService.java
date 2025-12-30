package com.se.wp.library.service.impl;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.se.wp.library.constants.Constants;
import com.se.wp.library.interfaces.IDocumentListingBusiness;
import com.se.wp.library.interfaces.rest.IDocumentListingBusinessRest;
import com.se.wp.library.models.DocumentListingResponseDto;
import com.se.wp.library.service.interfaces.IDocumentListingService;
import com.se.wp.library.soap.bsl.rest.util.BslDocumentUtilRest;
import com.se.wp.library.utils.DsuUtil;

@Service
public class DocumentListingService implements IDocumentListingService {
	private static final Logger LOGGER = LoggerFactory.getLogger(BslDocumentUtilRest.class);
	@Autowired
	IDocumentListingBusiness documentListingBusiness;
	@Autowired
	IDocumentListingBusinessRest documentListingBusinessRest;
	@Autowired
	private DsuUtil DsuUtil;
	@Override
	@Cacheable(value = Constants.DOCUMENT_LISTING_CACHE_NAME)
	public DocumentListingResponseDto getDocuments(String languageCode,Map<String, String> filters) throws IOException {
		if (DsuUtil.isApiGeeConsumer()) {
			LOGGER.debug("Apigee Consumer");
			return documentListingBusinessRest.getDocuments( languageCode, filters );
		} else {
			LOGGER.debug("SOAP Consumer");
		return documentListingBusiness.getDocuments(languageCode, filters);
	}
}
}
