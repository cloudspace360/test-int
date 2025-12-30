package com.se.wp.library.models.rest;

import java.util.List;
import java.util.Map;

public class DocumentListingResponseDto {
	private long documentsCount;
	private String dataSource;
	private List<DocumentListingDto> documents;
	private DocumentFilters documentFilters;
	private Map<String, String> sortByOptions;
	private String itemsPerPageOptions;
	private Map<String, Object> criteria;

	public long getDocumentsCount() {
		return documentsCount;
	}

	public void setDocumentsCount(long documentsCount) {
		this.documentsCount = documentsCount;
	}

	public List<DocumentListingDto> getDocuments() {
		return documents;
	}

	public void setDocuments(List<DocumentListingDto> documents) {
		this.documents = documents;
	}

	public Map<String, Object> getCriteria() {
		return criteria;
	}

	public void setCriteria(Map<String, Object> appliedFilters) {
		this.criteria = appliedFilters;
	}

	public DocumentFilters getDocumentFilters() {
		return documentFilters;
	}

	public void setDocumentFilters(DocumentFilters documentFilters) {
		this.documentFilters = documentFilters;
	}

	public Map<String, String> getSortByOptions() {
		return sortByOptions;
	}

	public void setSortByOptions(Map<String, String> sortByOptions) {
		this.sortByOptions = sortByOptions;
	}

	public String getItemsPerPageOptions() {
		return itemsPerPageOptions;
	}

	public void setItemsPerPageOptions(String itemsPerPageOptions) {
		this.itemsPerPageOptions = itemsPerPageOptions;
	}
	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}
}
