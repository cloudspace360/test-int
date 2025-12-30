package com.se.wp.library.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;

import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import com.se.wp.library.constants.Constants;
import com.se.wp.library.models.DocumentFile;
import com.se.wp.library.models.DocumentListingDto;
import com.se.wp.library.models.DocumentType;
import com.se.wp.library.models.GADownloadDataLayer;
import com.se.wp.library.models.GAPageViewDataLayer;

@Component
public class WpLibraryUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(WpLibraryUtil.class);
	@Autowired
	PropertiesUtil propertiesUtil;
	@Autowired
	EnvironmentUtil environmentUtil;
	private static final String[] FILE_SIZE_UNITS = new String[] { "bytes", "KB", "MB", "GB", "TB" };

	public Map<String, String> stringToMap(String pString, String splitter1, String splitter2) {
		Map<String, String> tokenInfo = new HashMap<>();
		if (pString != null && pString.contains(splitter1)) {
			String[] pairs = pString.split(splitter1);
			String key, value;
			String[] keyValue;
			for (int i = 0; i < pairs.length; i++) {
				keyValue = pairs[i].split(splitter2);
				key = keyValue[0];
				value = keyValue[1];
				LOGGER.debug("key: {}, value: {}", key, value);
				tokenInfo.put(key, value);
			}
		}
		return tokenInfo;
	}

	public String getLanguage(HttpServletRequest request) {
		String requestParamLc = request.getParameter("lc");
		if (requestParamLc != null && requestParamLc.length() == 2)
			return requestParamLc.toLowerCase();
		String language = "en";
		Enumeration<String> headers = request.getHeaderNames();
		LOGGER.debug("headers: {}", headers);
		LOGGER.debug("accept-language: {}", request.getHeader("accept-language"));
		Locale locale = getLocale();
		if (locale == null)
			LOGGER.debug("No locale found, returning default language - en");
		else
			language = locale.getLanguage();
		return language.toLowerCase();
	}

	public Locale getLocale() {
		Locale locale = LocaleContextHolder.getLocale();
		/*if (locale == null)
			LOGGER.debug("No locale found, returning null");
		else*/
		LOGGER.debug("locale.country: {} & locale.language: {}", locale.getCountry(), locale.getLanguage());
		return locale;
	}

	public List<Long> getLongFilter(String filterStr, String filterName) {
		LOGGER.debug("getLongFilter filterName: {}", filterName);
		List<Long> longFilter = null;
		if (filterStr != null && !filterStr.isEmpty()) {
			LOGGER.debug("getLongFilter filterStr: {}", filterStr);
			longFilter = new ArrayList<>();
			if (filterStr.contains(",")) {
				String[] filterArray = filterStr.split(",");
				for (int i = 0; i < filterArray.length; i++) {
					getLongAndAdd(filterArray[i], longFilter);
				}
			} else {
				getLongAndAdd(filterStr, longFilter);
			}
		}
		return longFilter;
	}

	protected void getLongAndAdd(String filterValue, List<Long> longFilter) {
		Long longValue = 0L;
		try {
			longValue = Long.parseLong(filterValue);
			longFilter.add(longValue);
		} catch (NumberFormatException nfe) {
			LOGGER.error("Error while parsing {} filter..", filterValue);
		}
	}

	public List<String> getStringFilter(String filterStr) {
		List<String> stringFilter = null;
		if (filterStr != null && !filterStr.isEmpty()) {
			stringFilter = new ArrayList<>();
			if (filterStr.contains(",")) {
				String[] productFiletrArray = filterStr.split(",");
				for (int i = 0; i < productFiletrArray.length; i++) {
					stringFilter.add(productFiletrArray[i]);
				}
			} else {
				stringFilter.add(filterStr);
			}
		}
		return stringFilter;
	}

	public String prepareDocumentTitle(String title, String reference) {
		String docTitle = title;
		if (docTitle == null || docTitle.isEmpty()) {
			docTitle = reference;
		}
		return parseHtml(docTitle);
	}

	private static String parseHtml(String data) {
		String parsedData = null;
		if (data != null && !data.isEmpty()) {
			parsedData = Jsoup.parse(data).text();
		}
		return parsedData;
	}

	public void setDescriptions(String pDescription, DocumentListingDto document) {
		LOGGER.debug("pDescription: {}", pDescription);
		String description = pDescription;
		String shortDescription = prepareShortDescription(pDescription);
		if (description.equals(shortDescription)) {
			description = null;
		}
		LOGGER.debug("description: {}", description);
		LOGGER.debug("shortDescription: {}", shortDescription);
		document.setDescription(parseHtml(description));
		document.setShortDescription(parseHtml(shortDescription));
	}

	public String prepareShortDescription(String pDocDescription) {
		LOGGER.debug("pDocDescription: {}", pDocDescription);
		String shortDescription = pDocDescription;
		if (pDocDescription != null && pDocDescription.length() > Constants.SHORT_DESCRIPTION_MAX_LENGTH) {
			shortDescription = pDocDescription.substring(0, 150);
			if (shortDescription.contains(Constants.DOT)) {
				if (!shortDescription.endsWith(Constants.DOT)) {
					shortDescription = shortDescription.substring(0, shortDescription.lastIndexOf(Constants.DOT) + 1)
							+ "..";
				} else {
					shortDescription = shortDescription + "..";
				}
			} else if (shortDescription.contains(" ")) {
				shortDescription = shortDescription.substring(0, shortDescription.lastIndexOf(" ")) + "...";
			} else {
				shortDescription = shortDescription + "...";
			}
		}
		return shortDescription;
	}

	public String prepareDocumentVersion(String version, String revision) {
		String documentVersion = null;
		if (revision != null && !revision.isEmpty()) {
			LOGGER.debug("Setting revision as doc version");
			documentVersion = revision;
		} else if (version != null && !version.isEmpty()) {
			LOGGER.debug("Setting version as doc version");
			documentVersion = version;
		}
		return documentVersion;
	}

	public String prepareImageUrl(String reference) {
		String imageUrl = null;
		String imageUrlTemplate = environmentUtil.getDocumentImageUrlTemplate();
		if (imageUrlTemplate != null && !imageUrlTemplate.isEmpty()) {
			imageUrl = imageUrlTemplate.replace("{DOCUMENT_REF_PARAM}", reference);
		}
		return imageUrl;
	}

	public String prepareDocumentDownloadUrl(List<DocumentFile> documentFiles, DocumentListingDto document) {
		String documentDownloadUrl = null;
		if (documentFiles != null && !documentFiles.isEmpty()) {
			LOGGER.debug("documentFiles not empty: {}", documentFiles.size());
			if (documentFiles.size() == 1) {
				LOGGER.debug("single file");
				documentDownloadUrl = documentFiles.get(0).getDownloadFileURL();
				document.setExtension(documentFiles.get(0).getExtension());
				document.setFileName(documentFiles.get(0).getFilename());
			} else {
				LOGGER.debug("archive file");
				document.setExtension(Constants.FILE_EXTENSION_ZIP);
				documentDownloadUrl = buildDownloadURL(document.getReference(),
						document.getDocumentType().getEnglishLabel(), "", true, document.isPrivate());
				document.setFileName(document.getReference() + Constants.ARCHIVE_FILE_EXTENSION);
			}
		}
		return documentDownloadUrl;
	}

	public String buildDownloadURL(String docReference, String docTypeEnglishLabel, String docFileName,
			boolean isArchive, boolean isPrivate) {
		LOGGER.debug("ENTER: OneDoewnloadUtil.buildDownloadURL()... docRef: {}, isPrivate: {}", docReference,
				isPrivate);
		String downloadUrl = null;
		if (docReference != null && docTypeEnglishLabel != null && docFileName != null) {
			String encodedDocReference = removeSpecialChars(docReference);
			String encodedDocType = removeSpecialChars(docTypeEnglishLabel);
			if (isArchive) {
				LOGGER.debug("build archive download url..");
				downloadUrl = environmentUtil.getArchiveDownloadUrlTemplate();
				downloadUrl = downloadUrl.replace("{DOCUMENT_REF_PARAM}", encodedDocReference);
				downloadUrl = downloadUrl.replace("{DOCUMENT_TYPE_PARAM}", encodedDocType);
				downloadUrl = downloadUrl.replace("{ARCHIVE_NAME_PARAM}",
						docReference + Constants.ARCHIVE_FILE_EXTENSION);
			} else {
				LOGGER.debug("build single download url..");
				downloadUrl = environmentUtil.getNonArchiveDownloadUrlTemplate();
				downloadUrl = downloadUrl.replace("{DOCUMENT_REF_PARAM}", encodedDocReference);
				downloadUrl = downloadUrl.replace("{DOCUMENT_TYPE_PARAM}", encodedDocType);
				downloadUrl = downloadUrl.replace("{FILE_NAME_PARAM}", docFileName);
			}
		}
		LOGGER.debug("Final downloadUrl: {}", downloadUrl);
		return downloadUrl;
	}

	public String removeSpecialChars(String source) {
		String encoded = null;
		try {
			return java.net.URLEncoder.encode(source, Constants.UTF8);
		} catch (UnsupportedEncodingException e) {
			LOGGER.error("Error while encoding download URL param: {}", e);
		}
		return encoded;
	}

	public void setOnlineAttributes(String bannerUrl, DocumentListingDto document) {
		LOGGER.debug("Doc ref: {}", document.getReference());
		if (bannerUrl != null && !bannerUrl.isEmpty()) {
			document.setBannerUrl(bannerUrl);
			document.setOnline(true);
			List<DocumentFile> documentFiles = document.getDocumentFiles();
			if (documentFiles != null && !documentFiles.isEmpty()) {
				DocumentType documentType = document.getDocumentType();
				if (document.getReference().startsWith(Constants.ONLINE_CAD_DOCUMENT_REFERENCE_START)
						&& documentType != null && documentType.getId() != 0L
						&& Constants.CAD_DOCUMENT_TYPE_IDS.contains(Long.toString(documentType.getId()))) {
					setDisableStatus(documentFiles, document);
				}
			} else {
				document.setDisableDownload(true);
			}
		}
	}

	protected void setDisableStatus(List<DocumentFile> list, DocumentListingDto document) {
		LOGGER.debug("setDisableStatus()...");
		boolean isOnlineCad = false;
		int size = list.size();
		String fileName;
		for (DocumentFile file : list) {
			fileName = file.getFilename();
			LOGGER.debug("checkDisableStatus: fileName: {}", fileName);
			if (fileName != null && fileName.endsWith(Constants.ONLINE_CAD_DOCUMENT_FILE_NAME_END)) {
				file.setDisableDownload(true);
				if (!isOnlineCad) {
					LOGGER.debug("ONLINE CAD");
					isOnlineCad = true;
					document.setOnlineCad(true);
				}
			}
		}
		document.setDisableDownload(isOnlineCad && size == 1);
	}

	public String prepareLocalizedSize(long size, String language) {
		java.util.Locale locale = new Locale(language);
		NumberFormat nf = NumberFormat.getInstance(locale);
		nf.setMaximumFractionDigits(1);
		if (size <= 0) {
			return "0";
		}
		int digitGroups = (int) (Math.log10(size) / Math.log10(1024));
		return nf.format(size / Math.pow(1024, digitGroups)) + " " + FILE_SIZE_UNITS[digitGroups];
	}

	public String prepareFileDownloadUrl(String language, String documentFileName,
			DocumentListingDto document) throws IOException {
		String downloadUrl;
		String docReference = document.getReference();
		DocumentType documentType = document.getDocumentType();
		StringBuilder docFileName = prepareDocumentFileName(language, docReference, documentType.getId(),
				documentFileName);
		downloadUrl = buildDownloadURL(docReference, documentType.getEnglishLabel(), docFileName.toString(), false,
				document.isPrivate());
		document.setEnDocType(documentType.getEnglishLabel());
		LOGGER.debug("EXIT prepareFileDownloadUrl...");
		return downloadUrl;
	}

	protected StringBuilder prepareDocumentFileName(String languageCode, String docReference,
			long docTypeId, String pFileName) throws IOException {
		StringBuilder docFileName = new StringBuilder();
		docFileName = new StringBuilder(pFileName);
		LOGGER.debug("docFileName: {}", docFileName.toString());
		LOGGER.debug("EXIT prepareDocumentFileName...");
		return docFileName;
	}

	public void addPageViewDataLayer(String countryCode, String languageCode, String refNum, String docType,
			Model model) {
		LOGGER.debug("ENTER: SchneiderUtil.addPageViewDataLayer()");
		try {
			model.addAttribute("gaPageViewEnabled", false);
			boolean ga4Enabled = Boolean
					.parseBoolean(propertiesUtil.getProperty("wp-library.properties", "ga.4.enabled"));
			model.addAttribute("ga4Enabled", ga4Enabled);
			if (Boolean.parseBoolean(propertiesUtil.getProperty("wp-library.properties", "ga.enabled"))) {
				model.addAttribute("gaPageViewEnabled", true);
				model.addAttribute("gtmId", propertiesUtil.getProperty("wp-library.properties", "gtm.id.ga3"));
				if (ga4Enabled)
					model.addAttribute("gtmId", propertiesUtil.getProperty("wp-library.properties", "gtm.id.ga4"));
				Map<String, String> gaPageViewDataLayerProperties = propertiesUtil
						.getAllProperties(Constants.GA_PAGE_VIEW_EVENT_FILE_NAME);
				if (null != gaPageViewDataLayerProperties && !gaPageViewDataLayerProperties.isEmpty()) {
					GAPageViewDataLayer gaPageViewDataLayer = new GAPageViewDataLayer();
					gaPageViewDataLayer
							.setEnvironment(propertiesUtil.getProperty("wp-library.properties", "ga.environemt"));
					gaPageViewDataLayer.setPageLanguage(languageCode.toUpperCase());
					gaPageViewDataLayer.setDigitalPlatformCountry(countryCode);
					gaPageViewDataLayer.setPageTitle(refNum);
					gaPageViewDataLayer.setPageCategoryId(docType);
					gaPageViewDataLayer.setEvent(gaPageViewDataLayerProperties.get("event"));
					gaPageViewDataLayer.setArticleTitle(gaPageViewDataLayerProperties.get("articleTitle"));
					gaPageViewDataLayer.setBusinessUnit(gaPageViewDataLayerProperties.get("businessUnit"));
					gaPageViewDataLayer.setDigitalPlatform(gaPageViewDataLayerProperties.get("digitalPlatform"));
					gaPageViewDataLayer.setFromWebView(gaPageViewDataLayerProperties.get("fromWebView"));
					gaPageViewDataLayer.setPageCategory(gaPageViewDataLayerProperties.get("pageCategory"));
					gaPageViewDataLayer.setPageProductId(gaPageViewDataLayerProperties.get("pageProductId"));
					gaPageViewDataLayer.setPageRangeId(gaPageViewDataLayerProperties.get("pageRangeId"));
					gaPageViewDataLayer.setPageSubCategory(gaPageViewDataLayerProperties.get("pageSubCategory"));
					gaPageViewDataLayer.setPageSubSubCategory(gaPageViewDataLayerProperties.get("pageSubSubCategory"));
					gaPageViewDataLayer.setPageTopCategory(gaPageViewDataLayerProperties.get("pageTopCategory"));
					gaPageViewDataLayer.setTemplate(gaPageViewDataLayerProperties.get("template"));
					gaPageViewDataLayer.setPageSubCategoryId(gaPageViewDataLayerProperties.get("pageSubCategoryId"));
					addDownloadDataLayer(model);
					model.addAttribute("pageViewDataLayer", gaPageViewDataLayer);
					LOGGER.debug("dataLayer: {}", gaPageViewDataLayer);
				}
				LOGGER.debug("EXIT: SchneiderUtil.addPageViewDataLayer()");
			}
		} catch (IOException e) {
			LOGGER.error("Error reading GA settings", e);
		}
	}

	private void addDownloadDataLayer(Model model) throws IOException {

		final String propertiesFileName = Constants.GA_DOWNLOAD_EVENT_FILE_NAME;
		Map<String, String> gaDownloadDataLayerProperties= propertiesUtil.getAllProperties(propertiesFileName);
		if(null!=gaDownloadDataLayerProperties && !gaDownloadDataLayerProperties.isEmpty() ) {
			GADownloadDataLayer gaDownloadDataLayer = new GADownloadDataLayer();
			gaDownloadDataLayer.setEvent(gaDownloadDataLayerProperties.get("event"));
			model.addAttribute("gaDownloadDataLayer", gaDownloadDataLayer);
		}
	}
	
	public Map<String,String> getMetaSpecificationData(){
		Map<String,String> metaConfigData = new HashMap<>();
		try {
			metaConfigData.putAll(propertiesUtil.getAllProperties(Constants.META_CONFIG_FILE_NAME));
		} catch (IOException e) {
			LOGGER.error("Error reading Meta Config properties");
		}
		
		return metaConfigData;
	}

	public void addOneTrustConfig(String language, Model model) {
		String oneTrustId = null;
		try {
			oneTrustId = propertiesUtil.getProperty("wp-library.properties", "one.trust.script.id");
		} catch (IOException e) {
			LOGGER.error("Error reading global one trust id.", e);
		}
		if ("zh".equalsIgnoreCase(language)) {
			try {
				oneTrustId = propertiesUtil.getProperty("document-types.properties",
						"one.trust.script.id." + language.toLowerCase());
			} catch (IOException e) {
				LOGGER.error("Error reading China one trust id.",e);
			}
		}
		if (oneTrustId != null && !oneTrustId.isEmpty()) {
			model.addAttribute("oneTrustId", oneTrustId);
		}
	}
}
