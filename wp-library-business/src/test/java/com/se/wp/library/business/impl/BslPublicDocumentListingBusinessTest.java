package com.se.wp.library.business.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.env.Environment;
import org.junit.jupiter.api.extension.ExtendWith;


import com.schneider.oreo.service.document.DocCountByFieldBean;
import com.schneider.oreo.service.document.DocumentPageBean;
import com.schneider.oreo.service.document.DocumentPageBean.DocumentCounts;
import com.schneider.oreo.service.document.DocumentPageBean.Documents;
import com.schneider.oreo.service.document.DocumentPageCountBean;
import com.schneider.oreo.service.document.DocumentPageCountResult;
import com.schneider.oreo.service.document.DocumentPageDocumentBean;
import com.schneider.oreo.service.document.TranslatedBean;
import com.se.wp.library.bsl.enumerations.Scope;
import com.se.wp.library.constants.Constants;
import com.se.wp.library.models.DocumentListingDto;
import com.se.wp.library.models.DocumentListingResponseDto;
import com.se.wp.library.models.DocumentTypeGroup;
import com.se.wp.library.soap.bsl.clinet.IBslDocumentService;
import com.se.wp.library.soap.bsl.util.BslDocumentServiceRequestUtil;
import com.se.wp.library.soap.bsl.util.BslDocumentServiceResponseUtil;
import com.se.wp.library.soap.bsl.util.BslSoapRequestUtil;
import com.se.wp.library.utils.EnvironmentUtil;
import com.se.wp.library.utils.WpLibraryUtil;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class BslPublicDocumentListingBusinessTest  {
	private String languageCode = "en";
	
	Map<String, String> requestFilters = new HashMap<>();
	DocumentPageBean documentPageBean;
	DocumentPageCountResult documentPageCountResult;
	DocumentPageCountBean documentPageCountBean;
	DocCountByFieldBean field;
	DocumentCounts documentCounts;
	List<DocumentListingDto> data;
	Documents documents;
	DocumentListingResponseDto dto;
	DocumentPageDocumentBean documentPageDocumentBean;
	TranslatedBean translatedBeanname;
	DocumentTypeGroup documentTypeGroup = new DocumentTypeGroup();
	List<DocumentTypeGroup> docTypeGroupList = new ArrayList<>();

	@InjectMocks
	BslPublicDocumentListingBusiness bslPublicDocumentListingBusiness;
	@Mock
	Environment env;
	
	@Mock
	WpLibraryUtil wpLibraryUtil;
	@Mock
	EnvironmentUtil environmentUtil;
	@Mock
	IBslDocumentService soapDocumentService;
	@Mock
	BslSoapRequestUtil bslSoapRequestUtil;
	@Mock
	BslDocumentServiceRequestUtil bslDocumentServiceRequestUtil;
	@Mock
	BslDocumentServiceResponseUtil bslDocumentServiceResponseUtil;
	@Mock
	DocumentListingResponseDto documentListingResponseDto;

	@BeforeEach
	public void setUp() throws Exception {
		
		translatedBeanname = new TranslatedBean();
		documentPageDocumentBean = new DocumentPageDocumentBean();
		documents = new Documents();
		documentPageBean = new DocumentPageBean();
		documentCounts = new DocumentCounts();
		requestFilters.put(Constants.DOC_TYPE, "12345");
		requestFilters.put(Constants.REF_NUM,"230");

		field = DocCountByFieldBean.ALL;
		documentPageCountBean = new DocumentPageCountBean();
		documentPageCountBean.setNumberOfDocs(104L);
		documentPageCountBean.setId("63732");
		translatedBeanname.setValue("translatedBeanvalue");
		translatedBeanname.setHighlighted("testHighlightedvalue");
		translatedBeanname.setValue("translatedBeanvalue");
		documentPageCountBean.setName(translatedBeanname);
		documentPageCountBean.setStatus("LEGACY");
		documentPageCountResult = new DocumentPageCountResult();
		documentPageCountResult.getCount().add(documentPageCountBean);
		documentPageCountResult.setField(field);
		documentCounts.getDocumentCount().add(documentPageCountResult);
		documentPageBean.setDocumentCounts(documentCounts);
		documentPageDocumentBean.setBannerUrl("TestBannerURL");
		documentPageDocumentBean.setReference("testReference");
		documentPageDocumentBean.setDocOid(1000L);
		documents.getDocument().add(documentPageDocumentBean);
		documentPageBean.setDocuments(documents);

	}

	@Test
	public void getBslPublicDocumentsTest() {

		try {
			Map<String, List<Long>> numericFilters = bslPublicDocumentListingBusiness.getNumericFilters(requestFilters);
			Map<String, List<String>> stringFilters = bslPublicDocumentListingBusiness.getStringFilters(requestFilters);
			String keyword = null;
			Scope scope = Scope.GLOBAL;
			Mockito.when(soapDocumentService.getDocumentPage(
					bslSoapRequestUtil.getDocumentServiceScopeBean(),
					bslSoapRequestUtil.getDocumentServiceLocaleBean(),
					bslDocumentServiceRequestUtil.getQueryBean(keyword,numericFilters,stringFilters),
					bslDocumentServiceRequestUtil.getPaginationBean(100, 1),1000,"9999",scope)).thenReturn(documentPageBean);
			Mockito.when(bslDocumentServiceResponseUtil.getDocumentListingResponse(languageCode, documentPageBean)).thenReturn(documentListingResponseDto);
			Mockito.when(environmentUtil.getBslVersion()).thenReturn("9999");
			bslPublicDocumentListingBusiness.getBslPublicDocuments(languageCode, requestFilters);
		} catch (IOException e) {
			fail();
		}
	}


}
