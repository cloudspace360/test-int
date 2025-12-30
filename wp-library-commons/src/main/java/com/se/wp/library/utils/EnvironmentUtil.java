package com.se.wp.library.utils;

import org.springframework.stereotype.Component;

/**
 * 
 * @author Vikas.Kadam
 *
 */
@Component
public class EnvironmentUtil {
	private String globalConfigLocation;
	private String publicBslServiceUrl;
	private String bslVersion;
	private String bslLocale;
	private String bslScopeBrand;
	private String bslScopeCountry;
	private String bslScopeProject;
	private String defaultProfile;
	private String activeProfile;
	private String documentImageUrlTemplate;
	private String nonArchiveDownloadUrlTemplate;
	private String archiveDownloadUrlTemplate;
	private static String apiGeeCoseEndpoint;
	private static String apiGeeCoseClientId;
	private static String apiGeeCoseClientSecret;
	
	private static String coseRequestConnectTimeout;
	private static String coseRequestReadTimeout;
	private static String coseEndpoint;
	
	private static String productRestEndpoint;
	private static String documentRestEndpoint;
	private static String guidedSearchRestEndpoint;
	private static String idmsEndpoint;
	private static String idmsHeaderAccept;
	private static String idmsHeaderContentType;
	private static String apiGeeRequestConnectTimeout;
	private static String apiGeeRequestReadTimeout;
	private static String salesForceRequestConnectTimeout;
	private static String salesForceRequestReadTimeout;
	private static String idmsRequestConnectTimeout;
	private static String idmsRequestReadTimeout;
	private static String useSalesForceAccessToken;
	
	private static String invalidFilterLabels;
	private static Boolean adjustBslDocDateTimeZone;
	

	public static String getInvalidFilterLabels() {
		return invalidFilterLabels;
	}


	public static Boolean getAdjustBslDocDateTimeZone() {
		return adjustBslDocDateTimeZone;
	}


	public static void setAdjustBslDocDateTimeZone(Boolean adjustBslDocDateTimeZone) {
		EnvironmentUtil.adjustBslDocDateTimeZone = adjustBslDocDateTimeZone;
	}


	public static void setInvalidFilterLabels(String invalidFilterLabels) {
		EnvironmentUtil.invalidFilterLabels = invalidFilterLabels;
	}

	public static String getIdmsEndpoint() {
		return idmsEndpoint;
	}

	public static void setIdmsEndpoint(String idmsEndpoint) {
		EnvironmentUtil.idmsEndpoint = idmsEndpoint;
	}

	public static String getIdmsHeaderAccept() {
		return idmsHeaderAccept;
	}

	public static void setIdmsHeaderAccept(String idmsHeaderAccept) {
		EnvironmentUtil.idmsHeaderAccept = idmsHeaderAccept;
	}

	public static String getIdmsHeaderContentType() {
		return idmsHeaderContentType;
	}

	public static void setIdmsHeaderContentType(String idmsHeaderContentType) {
		EnvironmentUtil.idmsHeaderContentType = idmsHeaderContentType;
	}

	public static String getApiGeeRequestConnectTimeout() {
		return apiGeeRequestConnectTimeout;
	}

	public static void setApiGeeRequestConnectTimeout(String apiGeeRequestConnectTimeout) {
		EnvironmentUtil.apiGeeRequestConnectTimeout = apiGeeRequestConnectTimeout;
	}

	public static String getApiGeeRequestReadTimeout() {
		return apiGeeRequestReadTimeout;
	}

	public static void setApiGeeRequestReadTimeout(String apiGeeRequestReadTimeout) {
		EnvironmentUtil.apiGeeRequestReadTimeout = apiGeeRequestReadTimeout;
	}

	public static String getSalesForceRequestConnectTimeout() {
		return salesForceRequestConnectTimeout;
	}

	public static void setSalesForceRequestConnectTimeout(String salesForceRequestConnectTimeout) {
		EnvironmentUtil.salesForceRequestConnectTimeout = salesForceRequestConnectTimeout;
	}

	public static String getSalesForceRequestReadTimeout() {
		return salesForceRequestReadTimeout;
	}

	public static void setSalesForceRequestReadTimeout(String salesForceRequestReadTimeout) {
		EnvironmentUtil.salesForceRequestReadTimeout = salesForceRequestReadTimeout;
	}

	public static String getIdmsRequestConnectTimeout() {
		return idmsRequestConnectTimeout;
	}

	public static void setIdmsRequestConnectTimeout(String idmsRequestConnectTimeout) {
		EnvironmentUtil.idmsRequestConnectTimeout = idmsRequestConnectTimeout;
	}

	public static String getIdmsRequestReadTimeout() {
		return idmsRequestReadTimeout;
	}

	public static void setIdmsRequestReadTimeout(String idmsRequestReadTimeout) {
		EnvironmentUtil.idmsRequestReadTimeout = idmsRequestReadTimeout;
	}

	public static String getUseSalesForceAccessToken() {
		return useSalesForceAccessToken;
	}

	public static void setUseSalesForceAccessToken(String useSalesForceAccessToken) {
		EnvironmentUtil.useSalesForceAccessToken = useSalesForceAccessToken;
	}

	public static String getApiGeeCoseEndpoint() {
		return apiGeeCoseEndpoint;
	}

	public static void setApiGeeCoseEndpoint(String apiGeeCoseEndpoint) {
		EnvironmentUtil.apiGeeCoseEndpoint = apiGeeCoseEndpoint;
	}

	public static String getApiGeeCoseClientId() {
		return apiGeeCoseClientId;
	}

	public static void setApiGeeCoseClientId(String apiGeeCoseClientId) {
		EnvironmentUtil.apiGeeCoseClientId = apiGeeCoseClientId;
	}

	public static String getApiGeeCoseClientSecret() {
		return apiGeeCoseClientSecret;
	}

	public static void setApiGeeCoseClientSecret(String apiGeeCoseClientSecret) {
		EnvironmentUtil.apiGeeCoseClientSecret = apiGeeCoseClientSecret;
	}

	public static String getCoseRequestConnectTimeout() {
		return coseRequestConnectTimeout;
	}

	public static void setCoseRequestConnectTimeout(String coseRequestConnectTimeout) {
		EnvironmentUtil.coseRequestConnectTimeout = coseRequestConnectTimeout;
	}

	public static String getCoseRequestReadTimeout() {
		return coseRequestReadTimeout;
	}

	public static void setCoseRequestReadTimeout(String coseRequestReadTimeout) {
		EnvironmentUtil.coseRequestReadTimeout = coseRequestReadTimeout;
	}

	public static String getCoseEndpoint() {
		return coseEndpoint;
	}

	public static void setCoseEndpoint(String coseEndpoint) {
		EnvironmentUtil.coseEndpoint = coseEndpoint;
	}

	public static String getProductRestEndpoint() {
		return productRestEndpoint;
	}

	public static void setProductRestEndpoint(String productRestEndpoint) {
		EnvironmentUtil.productRestEndpoint = productRestEndpoint;
	}

	public static String getDocumentRestEndpoint() {
		return documentRestEndpoint;
	}

	public static void setDocumentRestEndpoint(String documentRestEndpoint) {
		EnvironmentUtil.documentRestEndpoint = documentRestEndpoint;
	}

	public static String getGuidedSearchRestEndpoint() {
		return guidedSearchRestEndpoint;
	}

	public static void setGuidedSearchRestEndpoint(String guidedSearchRestEndpoint) {
		EnvironmentUtil.guidedSearchRestEndpoint = guidedSearchRestEndpoint;
	}

	public String getGlobalConfigLocation() {
		return globalConfigLocation;
	}

	public void setGlobalConfigLocation(String globalConfigLocation) {
		this.globalConfigLocation = globalConfigLocation;
	}

	public String getPublicBslServiceUrl() {
		return publicBslServiceUrl;
	}

	public void setPublicBslServiceUrl(String publicBslServiceUrl) {
		this.publicBslServiceUrl = publicBslServiceUrl;
	}

	public String getBslVersion() {
		return bslVersion;
	}

	public void setBslVersion(String bslVersion) {
		this.bslVersion = bslVersion;
	}

	public String getBslLocale() {
		return bslLocale;
	}

	public void setBslLocale(String bslLocale) {
		this.bslLocale = bslLocale;
	}

	public String getBslScopeBrand() {
		return bslScopeBrand;
	}

	public void setBslScopeBrand(String bslScopeBrand) {
		this.bslScopeBrand = bslScopeBrand;
	}

	public String getBslScopeCountry() {
		return bslScopeCountry;
	}

	public void setBslScopeCountry(String bslScopeCountry) {
		this.bslScopeCountry = bslScopeCountry;
	}

	public String getBslScopeProject() {
		return bslScopeProject;
	}

	public void setBslScopeProject(String bslScopeProject) {
		this.bslScopeProject = bslScopeProject;
	}

	public String getDefaultProfile() {
		return defaultProfile;
	}

	public void setDefaultProfile(String defaultProfile) {
		this.defaultProfile = defaultProfile;
	}

	public String getActiveProfile() {
		return activeProfile;
	}

	public void setActiveProfile(String activeProfile) {
		this.activeProfile = activeProfile;
	}

	public String getDocumentImageUrlTemplate() {
		return documentImageUrlTemplate;
	}

	public void setDocumentImageUrlTemplate(String documentImageUrlTemplate) {
		this.documentImageUrlTemplate = documentImageUrlTemplate;
	}

	public String getNonArchiveDownloadUrlTemplate() {
		return nonArchiveDownloadUrlTemplate;
	}

	public void setNonArchiveDownloadUrlTemplate(String nonArchiveDownloadUrlTemplate) {
		this.nonArchiveDownloadUrlTemplate = nonArchiveDownloadUrlTemplate;
	}

	public String getArchiveDownloadUrlTemplate() {
		return archiveDownloadUrlTemplate;
	}

	public void setArchiveDownloadUrlTemplate(String archiveDownloadUrlTemplate) {
		this.archiveDownloadUrlTemplate = archiveDownloadUrlTemplate;
	}
}
