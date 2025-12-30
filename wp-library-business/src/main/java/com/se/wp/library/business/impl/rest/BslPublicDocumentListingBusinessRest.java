package com.se.wp.library.business.impl.rest;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.schneider.rest.document.service.requests.DocumentPage;
import com.schneider.rest.document.service.requests.GetDocumentPage;
import com.schneider.rest.document.service.requests.Pagination;
import com.schneider.rest.document.service.responses.DocumentPageResponse;
import com.se.wp.library.bsl.enumerations.Scope;
import com.se.wp.library.constants.Constants;
import com.se.wp.library.models.DocumentListingResponseDto;
import com.se.wp.library.rest.bsl.client.IDocumentServiceRest;
import com.se.wp.library.soap.bsl.clinet.IBslDocumentService;
import com.se.wp.library.soap.bsl.rest.util.BslDocumentServiceRequestRestUtil;
import com.se.wp.library.soap.bsl.rest.util.BslDocumentServiceResponseRestUtil;
import com.se.wp.library.soap.bsl.rest.util.BslSoapRequestRestUtil;
import com.se.wp.library.soap.bsl.util.BslContants;
import com.se.wp.library.utils.EnvironmentUtil;
import com.se.wp.library.utils.PropertiesUtil;
import com.se.wp.library.utils.WpLibraryUtil;


@Component
public class BslPublicDocumentListingBusinessRest {
	private static final Logger LOGGER = LoggerFactory.getLogger(BslPublicDocumentListingBusinessRest.class);
	@Autowired
	WpLibraryUtil wpLibraryUtil;
	@Autowired
	IBslDocumentService bslDocumentService;
	@Autowired
	BslSoapRequestRestUtil bslSoapRequestRestUtil;
	@Autowired
	BslDocumentServiceRequestRestUtil bslDocumentServiceRequestUtil;
	@Autowired
	BslDocumentServiceResponseRestUtil bslDocumentServiceResponseUtil;
	@Autowired
	PropertiesUtil propertiesUtil;
	@Autowired
	EnvironmentUtil environmentUtil;
	@Autowired
	IDocumentServiceRest documentServiceRest;

	public DocumentListingResponseDto getRestPublicDocuments(String languageCode,Map<String, String> requestFilters) throws IOException {
		DocumentListingResponseDto docuemntListingResponse = null;
		LOGGER.debug("requestFilters2: {}", requestFilters);
		Scope scope = Scope.GLOBAL;
		String keyword = null;
		int pageNumber = 1;
		int itemsPerPage = 100;
		Map<String, List<Long>> numericFilters = getNumericFilters(requestFilters);
		LOGGER.debug("numericFilters1: {}", numericFilters);
		Map<String, List<String>> stringFilters = getStringFilters(requestFilters);
		LOGGER.debug("stringFiletrs1: {}", stringFilters);
		DocumentPage documentPage = new DocumentPage();
		prepareRequestData(languageCode,documentPage, numericFilters,stringFilters,keyword, scope,pageNumber,itemsPerPage);
		
				ResponseEntity<DocumentPageResponse> responseEntity = documentServiceRest.getDocumentPageRest(documentPage);
		if (responseEntity != null && responseEntity.getBody()!=null) {
			LOGGER.debug("responseEntity received...)");
	docuemntListingResponse = bslDocumentServiceResponseUtil.getDocuemntListingResponse(languageCode,responseEntity);
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
	private void prepareRequestData(String languageCode, DocumentPage documentPage,Map<String, List<Long>> numericFilters,Map<String, List<String>> stringFilters,String keyword,Scope scope,int pageNumber,int itemsPerPage) throws IOException {
		GetDocumentPage getDocumentPage = new GetDocumentPage();
		getDocumentPage.setScope(bslSoapRequestRestUtil.getProductServiceScope());
		getDocumentPage.setLocale(bslSoapRequestRestUtil.getProductServiceLocale());		
		getDocumentPage.setQuery(bslSoapRequestRestUtil.getQuery(keyword, numericFilters, stringFilters));
		Pagination pagination = bslSoapRequestRestUtil.getPagination(itemsPerPage,pageNumber);
		getDocumentPage.setPagination(pagination);
		getDocumentPage.setMaxCountResult(BslContants.MAX_COUNT_RESULTS);
		getDocumentPage.setVersion(
				environmentUtil.getBslVersion());
		documentPage.setGetDocumentPage(getDocumentPage);
		
	}

}
