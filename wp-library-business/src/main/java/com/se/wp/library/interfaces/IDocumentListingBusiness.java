package com.se.wp.library.interfaces;

import java.io.IOException;
import java.util.Map;

import com.se.wp.library.models.DocumentListingResponseDto;

@FunctionalInterface
public interface IDocumentListingBusiness {
	public DocumentListingResponseDto getDocuments(String languageCode,Map<String, String> filters) throws IOException;
}
