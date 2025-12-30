package com.se.wp.library.business.impl.rest;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.se.wp.library.interfaces.rest.IDocumentListingBusinessRest;
import com.se.wp.library.models.DocumentListingResponseDto;


@Component
public class DocumentListingBusinessRest implements IDocumentListingBusinessRest {

	@Autowired
	BslPublicDocumentListingBusinessRest bslPublicDocumentListingBusinessRest;

	@Override
	public DocumentListingResponseDto getDocuments(String languageCode,Map<String, String> requestFilters) throws IOException {
		
		return bslPublicDocumentListingBusinessRest.getRestPublicDocuments(languageCode,requestFilters);
	}
}