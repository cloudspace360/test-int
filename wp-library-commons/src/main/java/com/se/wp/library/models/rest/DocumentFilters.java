package com.se.wp.library.models.rest;

import java.util.List;


import com.se.wp.library.models.rest.DocumentType;
import com.se.wp.library.models.rest.DocumentTypeGroup;

public class DocumentFilters {
	private List<DocumentTypeGroup> documentTypeGroups;
	private List<DocumentType> documentTypes;
	
	
	public List<DocumentTypeGroup> getDocumentTypeGroups() {
		return documentTypeGroups;
	}

	public void setDocumentTypeGroups(List<DocumentTypeGroup> documentTypeGroups) {
		this.documentTypeGroups = documentTypeGroups;
	}

	public List<DocumentType> getDocumentTypes() {
		return documentTypes;
	}

	public void setDocumentTypes(List<DocumentType> documentTypes) {
		this.documentTypes = documentTypes;
	}

	
}
