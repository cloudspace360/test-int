package com.se.wp.library.models;

import java.util.List;

public class DocumentListingResponseDto {
	private long documentsCount;
	private List<DocumentListingDto> documents;

	public long getDocumentsCount() {
		return documentsCount;
	}

	public List<DocumentListingDto> getDocuments() {
		return documents;
	}

	public void setDocuments(List<DocumentListingDto> documents) {
		this.documents = documents;
	}

	public void setDocumentsCount(long documentsCount) {
		this.documentsCount = documentsCount;
	}
}
