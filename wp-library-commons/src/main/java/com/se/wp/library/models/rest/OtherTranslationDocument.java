package com.se.wp.library.models.rest;

import java.util.Collection;

public class OtherTranslationDocument {
	private String reference;
	private String title;
	private String documentDate;
	private String version;
	private String languages;
	private String size;
	private String downloadUrl;
	private boolean isStandard;
	private Collection<OtherTranslation> otherTranslations;
    private String checkSum;
	
	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getDocumentDate() {
		return documentDate;
	}

	public void setDocumentDate(String documentDate) {
		this.documentDate = documentDate;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	public Collection<OtherTranslation> getOtherTranslations() {
		return otherTranslations;
	}

	public void setOtherTranslations(Collection<OtherTranslation> otherTranslations) {
		this.otherTranslations = otherTranslations;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getLanguages() {
		return languages;
	}

	public void setLanguages(String languages) {
		this.languages = languages;
	}
	
	public String getDownloadUrl() {
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}
	
	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}
	
	public boolean isStandard() {
		return isStandard;
	}

	public void setStandard(boolean isStandard) {
		this.isStandard = isStandard;
	}
	
	public String getCheckSum() {
		return checkSum;
	}

	public void setCheckSum(String checkSum) {
		this.checkSum = checkSum;
	}
}
