package com.se.wp.library.models.rest;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

import com.se.wp.library.models.rest.DocumentFile;
import com.se.wp.library.models.DocumentType;
import com.se.wp.library.models.DocumentTypeGroup;
import com.se.wp.library.models.Range;

public class DocumentListingDto implements Comparator<DocumentListingDto>, Comparable<DocumentListingDto> {// DC-191
	private String dataSource;
	private String reference;
	private String encodedDocReference;
	private long documentId;
	private String downloadUrl;
	private String title;
	private String shortDescription;
	private String description;
	private String size;
	private int numberOfDoc;
	private String version;
	private boolean legacy;
	private String bannerUrl;
	private boolean online;
	private boolean onlineCad;
	private String documentDate;
	private String creationDate;
	private String lastModificationDate;
	private String publicationDate;
	private String expireDate;
	private boolean disableDownload;
	private DocumentType documentType;
	private String documentTypeDisplayLabel;
	private List<DocumentTypeGroup> docTypeGroups;
	private List<Range> ranges;
	private String locales;
	private List<DocumentFile> documentFiles;
	private String imageUrl;
	private String extension;
	private String cartAddedDate;

	private String docLanguages;
	private String keywords;
	private String partNumber;
	private int numberOfPage;
	private List<String> productReferences;
	private String docOwner;
	private String author;
	private String remark;
	private String shortKeyword;
	private String multiPartFileSizeAndType;
	private String metaKeywords;
	private String metaTitle;
	private List<String> documentScopes;
	private Set<String> alternateUrls;
	private String metaDescription;
	private String fileName;
	private String enDocType;
	private boolean isPrivate;
	private boolean hasPreviousVersion;
	private List<OtherVersionDocument> otherVersionDocuments;
	private boolean hasTranslations;
	private List<OtherTranslationDocument> OtherTranslationDocuments;
	
	private String operatingSystem;
	private List<String> subTypes;
	private List<String> organizations;
	private List<String> standards;
	private List<String> conformityInformation;
	private List<String> certificationCountries;
	private List<String> perimeters;
	private boolean hasSalesKit;
	private List<String> salesKitDocuments;
	private boolean indexable;
	private String revision;
	private boolean downloadTrackable;
	private String defaultFormattedDocumentDate;
	private String defaultFormattedLastModificationDate;
	private String languageCodes;
	private String localizedLanguageLabels;
	public String getLanguageCodes() {
		return languageCodes;
	}

	public void setLanguageCodes(String languageCodes) {
		this.languageCodes = languageCodes;
	}

	public String getLocalizedLanguageLabels() {
		return localizedLanguageLabels;
	}

	public void setLocalizedLanguageLabels(String localizedLanguageLabels) {
		this.localizedLanguageLabels = localizedLanguageLabels;
	}

	/**
	 * @return
	 */
	public String getMetaTitle() {
		return metaTitle;
	}

	/**
	 * @param metaTitle
	 */
	public void setMetaTitle(String metaTitle) {
		this.metaTitle = metaTitle;
	}

	/**
	 * @return
	 */
	public String getMetaDescription() {
		return metaDescription;
	}

	/**
	 * @param metaDescription
	 */
	public void setMetaDescription(String metaDescription) {
		this.metaDescription = metaDescription;
	}

	/**
	 * @return
	 */
	public String getMetaKeywords() {
		return metaKeywords;
	}

	/**
	 * @param metaKeywords
	 */
	public void setMetaKeywords(String metaKeywords) {
		this.metaKeywords = metaKeywords;
	}

	/**
	 * @return
	 */
	public String getShortKeyword() {
		return shortKeyword;
	}

	/**
	 * @param shortKeyword
	 */
	public void setShortKeyword(String shortKeyword) {
		this.shortKeyword = shortKeyword;
	}

	/**
	 * @return
	 */
	public String getDocLanguages() {
		return docLanguages;
	}

	/**
	 * @param docLanguages
	 */
	public void setDocLanguages(String docLanguages) {
		this.docLanguages = docLanguages;
	}

	/**
	 * @return
	 */
	public String getMultiPartFileSizeAndType() {
		return multiPartFileSizeAndType;
	}

	/**
	 * @param multiPartFileSizeAndType
	 */
	public void setMultiPartFileSizeAndType(String multiPartFileSizeAndType) {
		this.multiPartFileSizeAndType = multiPartFileSizeAndType;
	}

	public String getDownloadUrl() {
		return downloadUrl;
	}

	public void setDownloadUrl(String downloadUrl) {
		this.downloadUrl = downloadUrl;
	}

	/**
	 * @return
	 */
	public int getNumberOfDoc() {
		return numberOfDoc;
	}

	/**
	 * @param numberOfDoc
	 */
	public void setNumberOfDoc(int numberOfDoc) {
		this.numberOfDoc = numberOfDoc;
	}

	/**
	 * @return
	 */
	public String getExpireDate() {
		return expireDate;
	}

	/**
	 * @param expireDate
	 */
	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}

	/**
	 * @return
	 */
	public String getPartNumber() {
		return partNumber;
	}

	/**
	 * @param partNumber
	 */
	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}

	/**
	 * @return
	 */
	public long getDocumentId() {
		return documentId;
	}

	/**
	 * @param documentId
	 */
	public void setDocumentId(long documentId) {
		this.documentId = documentId;
	}

	/**
	 * @return
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return
	 */
	public String getReference() {
		return reference;
	}

	/**
	 * @param reference
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}

	/**
	 * @return
	 */
	public String getSize() {
		return size;
	}

	/**
	 * @param size
	 */
	public void setSize(String size) {
		this.size = size;
	}

	/**
	 * @return
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	/**
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return
	 */
	public String getDocOwner() {
		return docOwner;
	}

	/**
	 * @param docOwner
	 */
	public void setDocOwner(String docOwner) {
		this.docOwner = docOwner;
	}

	/**
	 * @return
	 */
	public String getKeywords() {
		return keywords;
	}

	/**
	 * @param keywords
	 */
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	/**
	 * @return
	 */
	public List<DocumentTypeGroup> getDocTypeGroups() {
		return docTypeGroups;
	}

	/**
	 * @param docTypeGroups
	 */
	public void setDocTypeGroups(List<DocumentTypeGroup> docTypeGroups) {
		this.docTypeGroups = docTypeGroups;
	}

	/**
	 * @return
	 */
	public DocumentType getDocumentType() {
		return documentType;
	}

	/**
	 * @param documentType
	 */
	public void setDocumentType(DocumentType documentType) {
		this.documentType = documentType;
	}

	/**
	 * @return
	 */
	public List<DocumentFile> getDocumentFiles() {
		return documentFiles;
	}

	/**
	 * @param files
	 */
	public void setDocumentFiles(List<DocumentFile> files) {
		this.documentFiles = files;
	}

	/**
	 * @return
	 */
	public String getCreationDate() {
		return creationDate;
	}

	/**
	 * @param creationDate
	 */
	public void setCreationDate(String creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @return
	 */
	public String getDocumentDate() {
		return documentDate;
	}

	/**
	 * @param documentDate
	 */
	public void setDocumentDate(String documentDate) {
		this.documentDate = documentDate;
	}

	/**
	 * @return
	 */
	public String getLastModificationDate() {
		return lastModificationDate;
	}

	/**
	 * @param lastModificationDate
	 */
	public void setLastModificationDate(String lastModificationDate) {
		this.lastModificationDate = lastModificationDate;
	}

	/**
	 * @return
	 */
	public String getLocales() {
		return locales;
	}

	/**
	 * @param locales
	 */
	public void setLocales(String locales) {
		this.locales = locales;
	}

	/**
	 * @return
	 */
	public int getNumberOfPage() {
		return numberOfPage;
	}

	/**
	 * @param numberOfPage
	 */
	public void setNumberOfPage(int numberOfPage) {
		this.numberOfPage = numberOfPage;
	}

	/**
	 * @return
	 */
	public List<String> getProductReferences() {
		return productReferences;
	}

	/**
	 * @param productReferences
	 */
	public void setProductReferences(List<String> productReferences) {
		this.productReferences = productReferences;
	}

	/**
	 * @return
	 */
	public String getPublicationDate() {
		return publicationDate;
	}

	/**
	 * @param publicationDate
	 */
	public void setPublicationDate(String publicationDate) {
		this.publicationDate = publicationDate;
	}

	/**
	 * @return
	 */
	public List<Range> getRanges() {
		return ranges;
	}

	/**
	 * @param ranges
	 */
	public void setRanges(List<Range> ranges) {
		this.ranges = ranges;
	}

	/**
	 * @return
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * @return
	 */
	public String getEncodedDocReference() {
		return encodedDocReference;
	}

	/**
	 * @param encodedDocReference
	 */
	public void setEncodedDocReference(String encodedDocReference) {
		this.encodedDocReference = encodedDocReference;
	}

	// DC-847 (New type of file format: html5)
	/**
	 * @return
	 */
	public String getBannerUrl() {
		return bannerUrl;
	}

	/**
	 * @param bannerUrl
	 */
	public void setBannerUrl(String bannerUrl) {
		this.bannerUrl = bannerUrl;
	}

	// START: DC-889
	/**
	 * @return
	 */
	public String getDocumentTypeDisplayLabel() {
		return documentTypeDisplayLabel;
	}

	/**
	 * @param documentTypeLabel
	 */
	public void setDocumentTypeDisplayLabel(String documentTypeDisplayLabel) {
		this.documentTypeDisplayLabel = documentTypeDisplayLabel;
	}

	/**
	 * 
	 * @return
	 */
	public List<String> getDocumentScopes() {
		return documentScopes;
	}

	/**
	 * 
	 * @param documentScopes
	 */
	public void setDocumentScopes(List<String> documentScopes) {
		this.documentScopes = documentScopes;
	}

	/**
	 * 
	 * @return
	 */
	public Set<String> getAlternateUrls() {
		return alternateUrls;
	}

	/**
	 * 
	 * @param alternateUrls
	 */
	public void setAlternateUrls(Set<String> alternateUrls) {
		this.alternateUrls = alternateUrls;
	}

	public boolean isDisableDownload() {
		return disableDownload;
	}

	public void setDisableDownload(boolean disableDownload) {
		this.disableDownload = disableDownload;
	}

	public boolean isOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}

	public boolean isOnlineCad() {
		return onlineCad;
	}

	public void setOnlineCad(boolean onlineCad) {
		this.onlineCad = onlineCad;
	}

	public String getOperatingSystem() {
		return operatingSystem;
	}

	public void setOperatingSystem(String operatingSystem) {
		this.operatingSystem = operatingSystem;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getExtension() {
		return extension;
	}

	public void setExtension(String extension) {
		this.extension = extension;
	}

	public boolean isLegacy() {
		return legacy;
	}

	public void setLegacy(boolean legacy) {
		this.legacy = legacy;
	}

	public String getCartAddedDate() {
		return cartAddedDate;
	}

	public void setCartAddedDate(String cartAddedDate) {
		this.cartAddedDate = cartAddedDate;
	}

	// END: DC-889
	// DC-191 Ravraj - Start
	// Overriding the compareTo method
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(DocumentListingDto doc) {
		return (this.title.toLowerCase()).compareTo(doc.title.toLowerCase());
	}

	// Overriding the compare method to sort the title
	@Override
	public int compare(DocumentListingDto doc1, DocumentListingDto doc2) {
		return doc1.getTitle().toLowerCase().compareTo(doc2.getTitle().toLowerCase());
	}
	// DC-191 Ravraj - End

	// Overriding equals() to compare two Document objects
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {

		// If the object is compared with itself then return true
		if (o == this) {
			return true;
		}

		/*
		 * Check if o is an instance of Document or not "null instanceof [type]" also
		 * returns false
		 */
		if (!(o instanceof DocumentListingDto)) {
			return false;
		}

		// typecast o to Document so that we can compare data members
		DocumentListingDto d = (DocumentListingDto) o;

		// Compare the data members and return accordingly
		return Long.compare(documentId, d.documentId) == 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return (int) ((documentId >> 32) ^ (documentId));
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getEnDocType() {
		return enDocType;
	}

	public void setEnDocType(String enDocType) {
		this.enDocType = enDocType;
	}

	public boolean isPrivate() {
		return isPrivate;
	}

	public void setPrivate(boolean isPrivate) {
		this.isPrivate = isPrivate;
	}
	
	public boolean isHasPreviousVersion() {
		return hasPreviousVersion;
	}

	public void setHasPreviousVersion(boolean hasPreviousVersion) {
		this.hasPreviousVersion = hasPreviousVersion;
	}
	
	public List<OtherVersionDocument> getOtherVersionDocuments() {
		return otherVersionDocuments;
	}
 
	public void setOtherVersionDocuments(List<OtherVersionDocument> otherVersionDocuments) {
		this.otherVersionDocuments = otherVersionDocuments;
	}
	
	public List<String> getSubTypes() {
		return subTypes;
	}

	public void setSubTypes(List<String> subTypes) {
		this.subTypes = subTypes;
	}
	
	public boolean isHasTranslations() {
		return hasTranslations;
	}

	public void setHasTranslations(boolean hasTranslations) {
		this.hasTranslations = hasTranslations;
	}
	
	public List<OtherTranslationDocument> getOtherTranslationDocuments() {
		return OtherTranslationDocuments;
	}

	public void setOtherTranslationDocuments(List<OtherTranslationDocument> otherTranslationDocuments) {
		OtherTranslationDocuments = otherTranslationDocuments;
	}
	
	public List<String> getOrganizations() {
		return organizations;
	}

	public void setOrganizations(List<String> organizations) {
		this.organizations = organizations;
	}

	public List<String> getStandards() {
		return standards;
	}

	public void setStandards(List<String> standards) {
		this.standards = standards;
	}

	public List<String> getConformityInformation() {
		return conformityInformation;
	}

	public void setConformityInformation(List<String> conformityInformation) {
		this.conformityInformation = conformityInformation;
	}

	public List<String> getCertificationCountries() {
		return certificationCountries;
	}

	public void setCertificationCountries(List<String> certificationCountries) {
		this.certificationCountries = certificationCountries;
	}

	public List<String> getPerimeters() {
		return perimeters;
	}

	public void setPerimeters(List<String> perimeters) {
		this.perimeters = perimeters;
	}
	
	public boolean isHasSalesKit() {
		return hasSalesKit;
	}

	public void setHasSalesKit(boolean hasSalesKit) {
		this.hasSalesKit = hasSalesKit;
	}
	
	public List<String> getSalesKitDocuments() {
		return salesKitDocuments;
	}

	public void setSalesKitDocuments(List<String> salesKitDocuments) {
		this.salesKitDocuments = salesKitDocuments;
	}
	

	public boolean isIndexable() {
		return indexable;
	}

	public void setIndexable(boolean indexable) {
		this.indexable = indexable;
	}

	public String getRevision() {
		return revision;
	}

	public void setRevision(String revision) {
		this.revision = revision;
	}

	public boolean isDownloadTrackable() {
		return downloadTrackable;
	}

	public void setDownloadTrackable(boolean downloadTrackable) {
		this.downloadTrackable = downloadTrackable;
	}
	
	public String getDefaultFormattedDocumentDate() {
		return defaultFormattedDocumentDate;
	}

	public void setDefaultFormattedDocumentDate(String defaultFormattedDocumentDate) {
		this.defaultFormattedDocumentDate = defaultFormattedDocumentDate;
	}
	
	public String getDefaultFormattedLastModificationDate() {
		return defaultFormattedLastModificationDate;
	}

	public void setDefaultFormattedLastModificationDate(String defaultFormattedLastModificationDate) {
		this.defaultFormattedLastModificationDate = defaultFormattedLastModificationDate;
	}

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}
}
