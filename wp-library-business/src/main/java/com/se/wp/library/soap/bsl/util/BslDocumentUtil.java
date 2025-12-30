package com.se.wp.library.soap.bsl.util;

import java.io.IOException;
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
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.schneider.oreo.service.document.DocumentPageAttributeListBean;
import com.schneider.oreo.service.document.DocumentPageBean.Documents;
import com.schneider.oreo.service.document.DocumentPageDocumentBean;
import com.schneider.oreo.service.document.DocumentPageDocumentBean.AttributeLists;
import com.schneider.oreo.service.document.DocumentPageDocumentBean.Files;
import com.schneider.oreo.service.document.DocumentPageDocumentBean.Locales;
import com.schneider.oreo.service.document.DocumentTypeBean;
import com.schneider.oreo.service.document.DocumentTypeGroupBean;
import com.schneider.oreo.service.document.FileBean;
import com.schneider.oreo.service.document.LocaleBean;
import com.schneider.oreo.service.document.TranslatedBean;
import com.se.wp.library.constants.Constants;
import com.se.wp.library.models.DocumentFile;
import com.se.wp.library.models.DocumentListingDto;
import com.se.wp.library.models.DocumentType;
import com.se.wp.library.models.DocumentTypeGroup;
import com.se.wp.library.utils.PropertiesUtil;
import com.se.wp.library.utils.WpLibraryUtil;

@Component
@Primary
public class BslDocumentUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(BslDocumentUtil.class);
	@Autowired
	PropertiesUtil propertiesUtil;
	@Autowired
	WpLibraryUtil wpLibraryUtil;

	public List<DocumentListingDto> prepareDocuments(String language, Documents documents)
			throws IOException {
		List<DocumentListingDto> documentsList = new ArrayList<>();
		if (documents != null && documents.getDocument() != null && !documents.getDocument().isEmpty()) {
			List<DocumentPageDocumentBean> documentPageDocumentBeanList = documents.getDocument();
			for (DocumentPageDocumentBean docBean : documentPageDocumentBeanList) {
				if (docBean != null && docBean.getReference() != null && !docBean.getReference().isEmpty()) {
					documentsList.add(prepareDocument(language, docBean));
				}
			}
		}
		return documentsList;
	}

	protected DocumentListingDto prepareDocument(String language, DocumentPageDocumentBean docBean)
			throws IOException {
		DocumentListingDto document = null;
		if (docBean != null) {
			document = new DocumentListingDto();
			document.setDocumentId(docBean.getDocOid());
			document.setReference(docBean.getReference());
			document.setTitle(wpLibraryUtil.prepareDocumentTitle(docBean.getTitle(), docBean.getReference()));
			String description = docBean.getDescription();
			if (description != null && !description.isEmpty()) {
				wpLibraryUtil.setDescriptions(description, document);
			}
			document.setVersion(wpLibraryUtil.prepareDocumentVersion(docBean.getVersion(), docBean.getRevision()));
			document.setImageUrl(wpLibraryUtil.prepareImageUrl(docBean.getReference()));
			Integer numberOfPage = docBean.getNumberOfPage();
			if (numberOfPage != null)
				setNumberOfPage(docBean.getNumberOfPage(), document);
			Locales locales = docBean.getLocales();
			if (locales != null) {
				setLanguageAttributes(language, locales.getLocale(), document);
			}
			setDocumentTypes(docBean.getDocumentType(), document);
			setDocumentDate(docBean.getDocumentDate(), document);
			Files files = docBean.getFiles();
			LOGGER.debug("BSL Document files: {}", files);
			if (files != null) {
				List<DocumentFile> documentFiles = populateDocumentFiles(language, files.getFile(), document);
				document.setDocumentFiles(documentFiles);
				document.setDownloadUrl(wpLibraryUtil.prepareDocumentDownloadUrl(documentFiles, document));
			}
			wpLibraryUtil.setOnlineAttributes(docBean.getBannerUrl(), document);
		}
		return document;
	}

	protected <T extends FileBean> List<DocumentFile> populateDocumentFiles(String language,
			List<T> fileBeans, DocumentListingDto document) throws IOException {
		List<DocumentFile> documentFiles = null;
		if (fileBeans != null && !fileBeans.isEmpty()) {
			LOGGER.debug("fileBeans size: {}", fileBeans.size());
			document.setNumberOfDoc(fileBeans.size());
			documentFiles = new ArrayList<>();
			DocumentFile documentFile;
			String fileName;
			Long fileSize;
			long totalFileSize = 0L;
			for (FileBean fileBean : fileBeans) {
				documentFile = new DocumentFile();
				documentFile.setId(fileBean.getId());
				fileName = fileBean.getFilename();
				documentFile.setFilename(fileName);
				documentFile.setExtension(fileBean.getExtension());
				fileSize = fileBean.getSize();
				if (fileSize != null) {
					totalFileSize = totalFileSize + fileSize;
					documentFile.setSize(wpLibraryUtil.prepareLocalizedSize(fileSize, language));
				}
				documentFile.setDownloadFileURL(
						wpLibraryUtil.prepareFileDownloadUrl(language, fileName, document));
				documentFiles.add(documentFile);
			}
			LOGGER.debug("totalFileSize: {}", totalFileSize);
			document.setSize(wpLibraryUtil.prepareLocalizedSize(totalFileSize, language));
		}
		return documentFiles;

	}

	protected void setDocumentDate(XMLGregorianCalendar xmlGeorgian,
			DocumentListingDto document) throws IOException {
		if (xmlGeorgian != null) {
			adjustTimeZone(xmlGeorgian);
			document.setDocumentDate(
					getFormattedDate(getDateObject(xmlGeorgian)));
		}
	}

	protected void setExpireDate(XMLGregorianCalendar expireDate,
			DocumentListingDto document) throws IOException {
		if (expireDate != null) {
			adjustTimeZone(expireDate);
			document.setExpireDate(
					getFormattedDate(getDateObject(expireDate)));
		}
	}

	protected void setPublicationDate(XMLGregorianCalendar publicationDate,
			DocumentListingDto document) throws IOException {
		if (publicationDate != null) {
			adjustTimeZone(publicationDate);
			document.setPublicationDate(
					getFormattedDate(getDateObject(publicationDate)));
		}
	}

	protected void setLastModificationDate( XMLGregorianCalendar lastModificationDate,
			DocumentListingDto document) throws IOException {
		if (lastModificationDate != null) {
			adjustTimeZone(lastModificationDate);
			document.setLastModificationDate(getFormattedDate(getDateObject(lastModificationDate)));
		}
	}

	protected void setCreationDate(String country, String language, XMLGregorianCalendar creationDate,
			DocumentListingDto document) throws IOException {
		if (creationDate != null) {
			adjustTimeZone(creationDate);
			document.setCreationDate(getFormattedDate(getDateObject(creationDate)));
		}
	}

	protected void adjustTimeZone(XMLGregorianCalendar xmlGeorgian) throws IOException {
		TimeZone.setDefault(TimeZone.getTimeZone(Constants.TIME_ZONE));
		LOGGER.debug("EXIT: DocumentUtil.adjustTimeZone()");
	}

	protected String getFormattedDate(Date dateObject) throws IOException {
		String formattedDate = null;
		if (dateObject != null) {
			formattedDate = getCountryFormattedDateString(dateObject);
		}
		return formattedDate;
	}

	protected Date getDateObject(XMLGregorianCalendar xmlGeorgian) throws IOException {
		return xmlGeorgian.toGregorianCalendar().getTime();
	}

	public String getCountryFormattedDateString(Date dateObject){
		LOGGER.debug("dateObject: {}", dateObject);
		SimpleDateFormat dateFormat = null;
		dateFormat = new SimpleDateFormat(Constants.DEFAULT_DATE_FORMAT);
		String formattedDateString = dateFormat.format(dateObject);
		LOGGER.debug("formattedDateString: {}", formattedDateString);
		LOGGER.debug("EXIT SchneiderUtil.getCountryFormattedDateString()");
		return formattedDateString;
	}

	protected void setNumberOfPage(Integer numberOfPage, DocumentListingDto document) {
		document.setNumberOfPage(numberOfPage);
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

	protected void setDocTypeGroups(List<DocumentTypeGroupBean> docTypeGroupBeanList, DocumentListingDto document) {
		List<DocumentTypeGroup> docTypeGroups = null;
		if (docTypeGroupBeanList != null && !docTypeGroupBeanList.isEmpty()) {
			docTypeGroups = new ArrayList<>();
			DocumentTypeGroup docTypeGroup;
			for (DocumentTypeGroupBean docTypeGroupBean : docTypeGroupBeanList) {
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

	protected List<String> getAttributeListValues(AttributeLists attributeLists, final String attributeListName) {
		List<String> attributeListValues = null;
		if (attributeLists != null && attributeLists.getAttributeList() != null
				&& !attributeLists.getAttributeList().isEmpty()) {
			List<DocumentPageAttributeListBean> attributeListBeanList = attributeLists.getAttributeList();
			attributeListValues = new ArrayList<>();
			for (DocumentPageAttributeListBean attributeListBean : attributeListBeanList) {
				if (attributeListName.equals(attributeListBean.getName())) {
					attributeListValues.addAll(getAttributeListValues(attributeListBean));
				}
			}
		}
		return attributeListValues;
	}

	protected List<String> getAttributeListValues(DocumentPageAttributeListBean attributeListBean) {
		List<String> attributeListValues = null;
		List<TranslatedBean> values = attributeListBean.getValues();
		if (values != null) {
			Iterator<TranslatedBean> itr = values.iterator();
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

	protected void setLanguageAttributes(String languageCode, List<LocaleBean> localeList,
			DocumentListingDto document) throws IOException {
		if (localeList != null && !localeList.isEmpty()) {
			SortedSet<String> localizedLanguageLabels = new TreeSet<>();
			SortedSet<String> isoLanguages = new TreeSet<>();
			List<String> locales = new ArrayList<>();
			String locale;
			for (LocaleBean localeBean : localeList) {
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
