package com.se.wp.library.business.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.schneider.oreo.service.document.DocumentPageBean;
import com.se.wp.library.bsl.enumerations.Scope;
import com.se.wp.library.constants.Constants;
import com.se.wp.library.models.DocumentListingResponseDto;
import com.se.wp.library.soap.bsl.clinet.IBslDocumentService;
import com.se.wp.library.soap.bsl.util.BslDocumentServiceRequestUtil;
import com.se.wp.library.soap.bsl.util.BslDocumentServiceResponseUtil;
import com.se.wp.library.soap.bsl.util.BslSoapRequestUtil;
import com.se.wp.library.utils.EnvironmentUtil;
import com.se.wp.library.utils.PropertiesUtil;
import com.se.wp.library.utils.WpLibraryUtil;

@Component
public class BslPublicDocumentListingBusiness {
	private static final Logger LOGGER = LoggerFactory.getLogger(BslPublicDocumentListingBusiness.class);
	@Autowired
	WpLibraryUtil wpLibraryUtil;
	@Autowired
	IBslDocumentService bslDocumentService;
	@Autowired
	BslSoapRequestUtil bslSoapRequestUtil;
	@Autowired
	BslDocumentServiceRequestUtil bslDocumentServiceRequestUtil;
	@Autowired
	BslDocumentServiceResponseUtil bslDocumentServiceResponseUtil;
	@Autowired
	PropertiesUtil propertiesUtil;
	@Autowired
	EnvironmentUtil environmentUtil;

	public DocumentListingResponseDto getBslPublicDocuments(String languageCode,Map<String, String> requestFilters) throws IOException {
		DocumentListingResponseDto docuemntListingResponse = null;
		LOGGER.debug("requestFilters2: {}", requestFilters);
		Scope scope = Scope.GLOBAL;
		int masResults = 1000;
		String keyword = null;
		int pageNumber = 1;
		int itemsPerPage = 100;
		Map<String, List<Long>> numericFilters = getNumericFilters(requestFilters);
		LOGGER.debug("numericFilters1: {}", numericFilters);
		Map<String, List<String>> stringFilters = getStringFilters(requestFilters);
		LOGGER.debug("stringFiletrs1: {}", stringFilters);
		String version = environmentUtil.getBslVersion();
		DocumentPageBean documentPageBean = bslDocumentService.getDocumentPage(
				bslSoapRequestUtil.getDocumentServiceScopeBean(),
				bslSoapRequestUtil.getDocumentServiceLocaleBean(),
				bslDocumentServiceRequestUtil.getQueryBean(keyword, numericFilters, stringFilters),
				bslDocumentServiceRequestUtil.getPaginationBean(itemsPerPage, pageNumber), masResults, version, scope);
		if (documentPageBean != null) {
			LOGGER.debug("DocumentPageBean received...)");
			docuemntListingResponse = bslDocumentServiceResponseUtil.getDocumentListingResponse(languageCode, documentPageBean);
		}
		return docuemntListingResponse;
	}

	protected Map<String, List<Long>> getNumericFilters(Map<String, String> filters) {
		Map<String, List<Long>> numericfilters = new HashMap<>();
		numericfilters.put(Constants.DOC_TYPE,
				wpLibraryUtil.getLongFilter(filters.get(Constants.DOC_TYPE), Constants.DOC_TYPE));
		return numericfilters;
	}

	protected Map<String, List<String>> getStringFilters(Map<String, String> filters) {
		Map<String, List<String>> stringFilters = new HashMap<>();
		stringFilters.put(Constants.REF_NUM, wpLibraryUtil.getStringFilter(filters.get(Constants.REF_NUM)));
		return stringFilters;
	}
}
