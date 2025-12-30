package com.se.wp.library.business.impl;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.se.wp.library.interfaces.IDocumentListingBusiness;
import com.se.wp.library.models.DocumentListingResponseDto;

@Component
public class DocumentListingBusiness implements IDocumentListingBusiness {

	@Autowired
	BslPublicDocumentListingBusiness bslPublicDocumentListingBusiness;

	@Override
	public DocumentListingResponseDto getDocuments(String languageCode,Map<String, String> requestFilters) throws IOException {
		DocumentListingResponseDto docuemntListingResponse;
		docuemntListingResponse = bslPublicDocumentListingBusiness.getBslPublicDocuments(languageCode,requestFilters);
		return docuemntListingResponse;
	}
}