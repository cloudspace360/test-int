package com.se.wp.library.service.interfaces;

import java.io.IOException;
import java.util.Map;

import com.se.wp.library.models.DocumentListingResponseDto;


public interface IDocumentListingService {

	public DocumentListingResponseDto getDocuments(String languageCode,Map<String, String> filters) throws IOException;
}
