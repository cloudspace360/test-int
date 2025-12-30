package com.se.wp.library.interfaces.rest;

import java.io.IOException;
import java.util.Map;

import com.se.wp.library.models.DocumentListingResponseDto;

@FunctionalInterface
public interface IDocumentListingBusinessRest {
	public DocumentListingResponseDto getDocuments(String languageCode,Map<String, String> filters) throws IOException;
}
