package com.se.wp.library.models.rest;

import java.util.Comparator;

public class OtherTranslation implements Comparable<OtherTranslation>, Comparator<OtherTranslation> {
	private String title;
	private String documentDate;
	private String version;
	private String language;
	private String locale;
	private String size;
	private String downloadUrl;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
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

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OtherTranslation otherTranslation = (OtherTranslation) obj;
		if (!this.locale.split("_")[0].equals(otherTranslation.locale.split("_")[0]))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		return locale.split("_")[0].hashCode();
	}

	@Override
	public int compareTo(OtherTranslation doc) {
		return (this.language).compareTo(doc.language);
	}

	@Override
	public int compare(OtherTranslation otr1, OtherTranslation otr2) {
		return otr1.getTitle().compareTo(otr2.getTitle());
	}
}
