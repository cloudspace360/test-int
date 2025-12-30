package com.se.wp.library.soap.bsl.rest.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TimeZone;
import java.util.TreeSet;

import javax.xml.datatype.XMLGregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.schneider.oreo.service.document.LocaleBean;
import com.schneider.rest.document.details.service.responses.Locale;
import com.schneider.rest.document.details.service.responses.Locales;
import com.schneider.rest.document.service.responses.Attribute;
import com.schneider.rest.document.service.responses.AttributeList;
import com.schneider.rest.document.service.responses.AttributeLists;
import com.schneider.rest.document.service.responses.Attributes;
import com.schneider.rest.document.service.responses.AudienceBean;
import com.schneider.rest.document.service.responses.DocTypeGroup;
import com.schneider.rest.document.service.responses.DocTypeGroups;
import com.schneider.rest.document.service.responses.Document;
import com.schneider.rest.document.service.responses.DocumentTypeBean;
import com.schneider.rest.document.service.responses.Documents;
import com.schneider.rest.document.service.responses.File;
import com.schneider.rest.document.service.responses.Files;
import com.schneider.rest.document.service.responses.ProductReference;
import com.schneider.rest.document.service.responses.ProductReferences;
import com.schneider.rest.document.service.responses.Values;
import com.se.wp.library.constants.Constants;
import com.se.wp.library.models.DocumentType;
import com.se.wp.library.models.DocumentTypeGroup;
import com.se.wp.library.models.DocumentFile;
import com.se.wp.library.models.DocumentListingDto;
import com.se.wp.library.soap.bsl.util.BslContants;
import com.se.wp.library.utils.DsuUtil;
import com.se.wp.library.utils.PropertiesUtil;
import com.se.wp.library.utils.WpLibraryUtil;
import com.se.wp.library.utils.EnvironmentUtil;
import com.se.wp.library.utils.rest.WpLibraryUtilRest;

@Component
@Primary
public class BslDocumentUtilRest {
	private static final Logger LOGGER = LoggerFactory.getLogger(BslDocumentUtilRest.class);
	@Autowired
	PropertiesUtil propertiesUtil;
	@Autowired
	WpLibraryUtilRest wpLibraryUtilRest;
	@Autowired
	EnvironmentUtil environmentUtil;
	@Value("${set.product.references.in.document.listing.response}")
	boolean setProductReferencesInDocumentListingResponse;
	@Value("${apply.max.limit.for.product.references.in.document.details.response}")
	boolean applyMaxLimitForProductReferencesInDocumentDetailsResponse;
	@Value("${max.limit.for.product.references.in.document.details.response}")
	int maxLimitForProductReferencesInDocumentDetailsResponse;
	@Value("${restricted.download.document}")
	boolean restrictedDownloadDocument;


	public List<DocumentListingDto> prepareDocuments(String countryCode, String languageCode,
			List<Document> documentList) throws IOException {
		List<DocumentListingDto> results = new ArrayList<>();
		if (documentList != null && !documentList.isEmpty()) {
			LOGGER.debug("coseDocsList size: {}", documentList.size());
			for (Document doc : documentList) {
				if (doc != null && doc.getReference() != null && !doc.getReference().isEmpty()) {
					results.add(prepareDocumentModel(countryCode, languageCode, doc));
				}
			}
		}
		LOGGER.debug("EXIT: DocumentServiceRestResponseUtil.prepareDocuments()");
		return results;
	}

	protected DocumentListingDto prepareDocumentModel(String country, String language, Document docBean)
			throws IOException {
		DocumentListingDto document = null;
		if (docBean != null) {
			document = new DocumentListingDto();
			document.setDocumentId(docBean.getDocOid());
			document.setReference(docBean.getReference());
			document.setTitle(wpLibraryUtilRest.prepareDocumentTitle(docBean.getTitle(), docBean.getReference()));
			String description = docBean.getDescription();
			if (description != null && !description.isEmpty()) {
				wpLibraryUtilRest.setDescriptions(description, document);
			}
			document.setVersion(wpLibraryUtilRest.prepareDocumentVersion(docBean.getVersion(), docBean.getRevision()));
			document.setRevision(docBean.getRevision());
			document.setImageUrl(
					wpLibraryUtilRest.prepareImageUrl(docBean.getReference()));
//			AttributeLists docAttributeLists = docBean.getAttributeLists();
//			if (docAttributeLists != null && docAttributeLists.getAttributeList() != null) {
//				setOperatingSystem(docAttributeLists, document);
//				document.setSubTypes(
//						getAttributeListValues(docAttributeLists, BslContants.BSL_DOCUMENT_CONTENT_ATTRIBUTE_NAME));
//				document.setOrganizations(getAttributeListValues(docAttributeLists,
//						BslContants.BSL_DOCUMENT_ORGANIZATION_ATTRIBUTE_NAME));
//				document.setStandards(
//						getAttributeListValues(docAttributeLists, BslContants.BSL_DOCUMENT_STANDARD_ATTRIBUTE_NAME));
//				document.setConformityInformation(getAttributeListValues(docAttributeLists,
//						BslContants.BSL_DOCUMENT_CONFORMITY_INFORMATION_ATTRIBUTE_NAME));
//				document.setCertificationCountries(getAttributeListValues(docAttributeLists,
//						BslContants.BSL_DOCUMENT_CERTIFICATION_COUNTRIES_ATTRIBUTE_NAME));
//				document.setPerimeters(
//						getAttributeListValues(docAttributeLists, BslContants.BSL_DOCUMENT_PERIMETER_ATTRIBUTE_NAME));
//			}
//			Attributes attributes = docBean.getAttributes();
//			if (attributes != null) {
//				setSalesKitDocuments(
//						getAttributeValues(attributes, BslContants.BSL_DOCUMENT_SALES_KIT_DOCUMENTS_ATTRIBUTE_NAME),
//						document);
//			}
			Integer numberOfPage = docBean.getNumberOfPage();
			if (numberOfPage != null)
				setNumberOfPage(docBean.getNumberOfPage(), document);
			ProductReferences productReferences = docBean.getProductReferences();
			if (productReferences != null && setProductReferencesInDocumentListingResponse) {
				setProductReferences(productReferences.getProductReference(), document);
			}
			
			Locales locales = docBean.getLocales();
			if (locales != null) {
				setLanguageAttributes(language, locales.getLocale(), document);
			}
			DocTypeGroups docTypeGroups = docBean.getDocTypeGroups();
			if (docTypeGroups != null) {
				setDocTypeGroups(docTypeGroups.getDocTypeGroup(), document);
			}
			setDocumentTypes(docBean.getDocumentType(), document);
			setDocumentDate(country, language, docBean.getDocumentDate(), document);
			setLastModificationDate(country, language, docBean.getLastModificationDate(), document);
			Files files = docBean.getFiles();
			LOGGER.debug("BSL Document files: {}", files);
			if (files != null) {
				List<DocumentFile> documentFiles = populateDocumentFiles(country, language, files.getFile(), document);
				document.setDocumentFiles(documentFiles);
				document.setDownloadUrl(wpLibraryUtilRest.prepareDocumentDownloadUrl(documentFiles, document));
			}
			wpLibraryUtilRest.setOnlineAttributes(docBean.getBannerUrl(), document);
			setPreviousVersionFields(docBean, document);
			setOtherTranslationFields(docBean, document);
		}
		return document;
	}


	protected void setExpireDate(String country, String language, XMLGregorianCalendar expireDate,
			DocumentListingDto document) throws IOException {
		if (expireDate != null) {
			adjustTimeZone(expireDate);
			document.setExpireDate(
					getFormattedDate(country, language, getDateObject(expireDate, document.getDocumentType())));
		}
	}

	protected void setPublicationDate(String country, String language, XMLGregorianCalendar publicationDate,
			DocumentListingDto document) throws IOException {
		if (publicationDate != null) {
			adjustTimeZone(publicationDate);
			document.setPublicationDate(
					getFormattedDate(country, language, getDateObject(publicationDate, document.getDocumentType())));
		}
	}

	protected void setCreationDate(String country, String language, XMLGregorianCalendar creationDate,
			DocumentListingDto document) throws IOException {
		if (creationDate != null) {
			adjustTimeZone(creationDate);
			document.setCreationDate(
					getFormattedDate(country, language, getDateObject(creationDate, document.getDocumentType())));
		}
	}


	protected List<String> setMaxProdReferences(List<String> productRefList) {
		List<String> maxProductRefList=productRefList;
		if (applyMaxLimitForProductReferencesInDocumentDetailsResponse
				&& productRefList.size() > maxLimitForProductReferencesInDocumentDetailsResponse) {
			maxProductRefList = productRefList.subList(0, maxLimitForProductReferencesInDocumentDetailsResponse);
		}
		return maxProductRefList;
	}
	
	protected String getTitle(List<String> attributeValues) {
		String title = null;
		if (attributeValues != null && !attributeValues.isEmpty()) {
			for (String attributeName : attributeValues) {
				if (attributeName != null) {
					title = attributeName;
				}
			}
		}
		return title;
	}
	
	protected String getDescription(List<String> attributeValues) {
		String description = null;
		if (attributeValues != null && !attributeValues.isEmpty()) {
			for (String attributeName : attributeValues) {
				if (attributeName != null) {
					description = attributeName;
				}
			}
		}
		return description;
	}
	
	
	private void setOtherTranslationFields(Document docBean, DocumentListingDto document) {
		boolean hasHasTranslations = false;
		Boolean hasHasTranslationsFlag = docBean.isHasTranslations();
		if (hasHasTranslationsFlag != null) {
			hasHasTranslations = hasHasTranslationsFlag;
		}
		document.setHasTranslations(hasHasTranslations);
	} 
	
	private void setPreviousVersionFields(Document docBean, DocumentListingDto document) {
		boolean hasPreviousVersion = false;
		Boolean hasPreviousVersionFlag = docBean.isHasPreviousVersion();
		if (hasPreviousVersionFlag != null) {
			hasPreviousVersion = hasPreviousVersionFlag;
		}
		document.setHasPreviousVersion(hasPreviousVersion);
	}
	protected <T extends File> List<DocumentFile> populateDocumentFiles(String country, String language,
			List<T> fileBeans, DocumentListingDto document) throws IOException {
		List<DocumentFile> documentFiles = null;
		if (fileBeans != null && !fileBeans.isEmpty()) {
			LOGGER.debug("fileBeans size: {}", fileBeans.size());
			LOGGER.debug("document.getReference(): {}", document.getReference());
			document.setNumberOfDoc(fileBeans.size());
			documentFiles = new ArrayList<>();
			DocumentFile documentFile;
			String fileName;
			Long fileSize;
			long totalFileSize = 0L;
			for (File fileBean : fileBeans) {
				documentFile = new DocumentFile();
				documentFile.setId(fileBean.getId());
				fileName = fileBean.getFilename();
				documentFile.setFilename(fileName);
				documentFile.setExtension(fileBean.getExtension());
				if (fileBean.getSize() != null) {
					fileSize = (long)fileBean.getSize();
					totalFileSize = totalFileSize + fileSize;
					documentFile.setSize(wpLibraryUtilRest.prepareLocalizedSize(fileSize, language));
				}
				documentFile.setDownloadFileURL(
						wpLibraryUtilRest.prepareFileDownloadUrl(language, fileName, document));
				documentFile.setCheckSum(fileBean.getSha256());
				documentFiles.add(documentFile);
			}
			LOGGER.debug("totalFileSize: {}", totalFileSize);
			document.setSize(wpLibraryUtilRest.prepareLocalizedSize(totalFileSize, language));
		}
		return documentFiles;

	}

	protected List<String> getAttributeListValues(AttributeLists attributeLists, final String attributeListName) {
		List<String> attributeListValues = null;
		if (attributeLists != null && attributeLists.getAttributeList() != null
				&& !attributeLists.getAttributeList().isEmpty()) {
			List<AttributeList> attributeListBeanList = attributeLists.getAttributeList();
			attributeListValues = new ArrayList<>();
			for (AttributeList attributeListBean : attributeListBeanList) {
				if (attributeListName.equals(attributeListBean.getName())) {
					attributeListValues.addAll(getAttributeListValues(attributeListBean));
				}
			}
		}
		return attributeListValues;
	}
	
	protected List<String> getAttributeListValues(AttributeList attributeListBean) {
		List<String> attributeListValues = null;
		List<Values> values = attributeListBean.getValues();
		if (values != null) {
			Iterator<Values> itr = values.iterator();
			attributeListValues = new ArrayList<>();
			String attributeListValue;
			while (itr.hasNext()) {
				attributeListValue = itr.next().getValue();
				if (attributeListValue != null && !attributeListValue.isEmpty())
					attributeListValues.add(attributeListValue);
			}
		}
		return attributeListValues;
	}

	private List<String> getAttributeValues(Attributes attributes, String attributeName) {
		final List<String> attributeValues = new ArrayList<>();
		List<Attribute> documentPageAttributeBean = attributes.getAttribute();
		if (documentPageAttributeBean != null) {
			documentPageAttributeBean.forEach(attribute -> {
				if (attributeName.equals(attribute.getName())) {
					attributeValues.add(attribute.getValue().getValue());
				}
			});
		}
		return attributeValues;
	}
	protected void setNumberOfPage(Integer numberOfPage, DocumentListingDto document) {
		document.setNumberOfPage(numberOfPage);
	}

	protected void setProductReferences(List<ProductReference> documentPageProductReferenceBeanList, DocumentListingDto document) {
		if (documentPageProductReferenceBeanList != null && !documentPageProductReferenceBeanList.isEmpty()) {
			List<String> productRefList = new ArrayList<>();
			for(ProductReference documentPageProductReferenceBean: documentPageProductReferenceBeanList) {
				productRefList.add(documentPageProductReferenceBean.getCommRef());
			}
			document.setProductReferences(productRefList);
 		}
 	}
	
	
	protected void setDocTypeGroups(List<DocTypeGroup> docTypeGroupBeanList, DocumentListingDto document) {
		List<DocumentTypeGroup> docTypeGroups = null;
		if (docTypeGroupBeanList != null && !docTypeGroupBeanList.isEmpty()) {
			docTypeGroups = new ArrayList<>();
			DocumentTypeGroup docTypeGroup;
			for (DocTypeGroup docTypeGroupBean : docTypeGroupBeanList) {
				if (docTypeGroupBean != null) {
					docTypeGroup = new DocumentTypeGroup();
					docTypeGroup.setId(docTypeGroupBean.getId());
					docTypeGroup.setLabel(docTypeGroupBean.getTranslation());
					docTypeGroups.add(docTypeGroup);
				}
			}
			Collections.sort(docTypeGroups);
		}
		document.setDocTypeGroups(docTypeGroups);
	}
	
	protected void setDocumentTypes(DocumentTypeBean documentTypeBean, DocumentListingDto document) {
		if (documentTypeBean != null) {
			document.setDocumentType(getDocumentTypeFromDocumentTypeBean(documentTypeBean));
			String documentTypeLabel = Constants.DOC_TYPE;
			String label = documentTypeBean.getTranslation();
			String name = documentTypeBean.getName();
			String englishLabel = documentTypeBean.getEnglishLabel();
			if (label != null && !label.isEmpty()) {
				documentTypeLabel = label;
			} else if (name != null && !name.isEmpty()) {
				documentTypeLabel = name;
			} else if (englishLabel != null && !englishLabel.isEmpty()) {
				documentTypeLabel = englishLabel;
			}
			document.setDocumentTypeDisplayLabel(documentTypeBean.getId() + Constants.HYPHEN + documentTypeLabel);
		}
	}
	
	protected DocumentType getDocumentTypeFromDocumentTypeBean(DocumentTypeBean documentTypeBean) {
		DocumentType docType = null;
		if (documentTypeBean != null) {
			docType = new DocumentType();
			docType.setId(documentTypeBean.getId());
			docType.setLabel(documentTypeBean.getTranslation());
			docType.setEnglishLabel(documentTypeBean.getEnglishLabel());
			docType.setName(documentTypeBean.getName());
		}
		return docType;
	}
	
	protected void setDocumentDate(String country, String language, XMLGregorianCalendar xmlGeorgian,
			DocumentListingDto document) throws IOException {
		if (xmlGeorgian != null) {
			adjustTimeZone(xmlGeorgian);
			document.setDocumentDate(
					getFormattedDate(country, language, getDateObject(xmlGeorgian, document.getDocumentType())));
		}
	}
	protected void adjustTimeZone(XMLGregorianCalendar xmlGeorgian) throws IOException {
		TimeZone.setDefault(TimeZone.getTimeZone(Constants.TIME_ZONE));
		LOGGER.debug("EXIT: DocumentUtil.adjustTimeZone()");
	}
	
	protected Date getDateObject(XMLGregorianCalendar xmlGeorgian, DocumentType documentType) throws IOException {
		Date dateObject = xmlGeorgian.toGregorianCalendar().getTime();
		
		return dateObject;
	}

	protected String getFormattedDate(String country, String language, Date dateObject) throws IOException {
		String formattedDate = null;
		if (dateObject != null) {
			formattedDate = getCountryFormattedDateString(country, language, dateObject);
		}
		return formattedDate;
	}
	public String getCountryFormattedDateString(String country, String language, Date dateObject) throws IOException {
		LOGGER.debug("dateObject: {}", dateObject);
		SimpleDateFormat dateFormat = null;
		dateFormat = new SimpleDateFormat(Constants.DEFAULT_DATE_FORMAT);
		String formattedDateString = dateFormat.format(dateObject);
		LOGGER.debug("formattedDateString: {}", formattedDateString);
		LOGGER.debug("EXIT SchneiderUtil.getCountryFormattedDateString()");
		return formattedDateString;
	}

	public String checkDateFormat(String format) {
		try {
			byte[] dateBytes = format.getBytes(Constants.ISO_8859_1);
			return new String(dateBytes, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			LOGGER.info("UnsupportedEncodingException {}", e.getMessage());
			LOGGER.info("SchneiderUtil:: {}", e);
			return Constants.DEFAULT_DATE_FORMAT;
		}
	}
	
	protected void setLastModificationDate(String country, String language, XMLGregorianCalendar lastModificationDate,
			DocumentListingDto document) throws IOException {
		if (lastModificationDate != null) {
			adjustTimeZone(lastModificationDate);
			document.setLastModificationDate(getFormattedDate(country, language,
					getDateObject(lastModificationDate, document.getDocumentType())));
		}
	}
	protected void setLanguageAttributes(String languageCode, List<Locale> localeList,
			DocumentListingDto document) throws IOException {
		if (localeList != null && !localeList.isEmpty()) {
			SortedSet<String> localizedLanguageLabels = new TreeSet<>();
			SortedSet<String> isoLanguages = new TreeSet<>();
			List<String> locales = new ArrayList<>();
			String locale;
			for (Locale localeBean : localeList) {
				if (localeBean != null) {
					locale = localeBean.getIsoLanguage() + "_" + localeBean.getIsoCountry();
					locales.add(locale);
					isoLanguages.add(localeBean.getIsoLanguage());
					try {
						localizedLanguageLabels.add(propertiesUtil.getProperty(languageCode, Constants.LABELS_FILE_NAME,
								"prm.wpl.language.label." + locale));
					}catch(Exception e){
						localizedLanguageLabels.add(propertiesUtil.getProperty("en", Constants.LABELS_FILE_NAME,
								"prm.wpl.language.label." + locale));
					}
				}
			}
			document.setLocales(String.join(", ", locales));
			document.setLanguageCodes(String.join(", ", isoLanguages));
			document.setLocalizedLanguageLabels(String.join(", ", localizedLanguageLabels));
		}
	}
}
