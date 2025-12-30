package com.se.wp.library.boot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.se.wp.library.utils.EnvironmentUtil;

/**
 * 
 * @author Vikas.Kadam
 *
 */
@Component
public class ApplicationInitializer implements InitializingBean {

	private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationInitializer.class);
	@Autowired
	Environment env;
	@Autowired
	EnvironmentUtil environmentUtil;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		LOGGER.debug("afterPropertiesSet");
		environmentUtil.setGlobalConfigLocation(env.getProperty("global.config.path"));
		environmentUtil.setPublicBslServiceUrl(env.getProperty("public.bsl.service.url"));
		environmentUtil.setBslVersion(env.getProperty("bsl.version"));
		environmentUtil.setBslScopeBrand(env.getProperty("bsl.scope.brand"));
		environmentUtil.setBslScopeCountry(env.getProperty("bsl.scope.country"));
		environmentUtil.setBslScopeProject(env.getProperty("bsl.scope.project"));
		environmentUtil.setBslLocale(env.getProperty("bsl.locale"));
		environmentUtil.setDocumentImageUrlTemplate(env.getProperty("document.image.url.template"));
		environmentUtil.setNonArchiveDownloadUrlTemplate(env.getProperty("non.archive.download.url.template"));
		environmentUtil.setArchiveDownloadUrlTemplate(env.getProperty("archive.download.url.template"));
		setProfiles();
		setApiGeeCoseProperties();
		setEndPoints();
		setRestTimeOut();
	}

	private void setProfiles() {
		StringBuilder defailtProfile = new StringBuilder();
		StringBuilder activeProfile = new StringBuilder();
		String[] defaultProfiles = env.getDefaultProfiles();
		if (defaultProfiles != null && defaultProfiles.length > 0) {
			for (String profile : defaultProfiles) {
				defailtProfile.append(profile).append(",");
			}
		}
		LOGGER.debug("default profile: {}", defailtProfile.toString());
		environmentUtil.setDefaultProfile(defailtProfile.toString());
		String[] activeProfiles = env.getActiveProfiles();
		if (activeProfiles != null && activeProfiles.length > 0) {
			for (String profile : activeProfiles) {
				activeProfile.append(profile).append(",");
			}
		}
		environmentUtil.setActiveProfile(activeProfile.toString());
		LOGGER.debug("active profile: {}", activeProfile.toString());
	}
	private void setApiGeeCoseProperties() {
		EnvironmentUtil.setApiGeeCoseEndpoint(env.getProperty("apigee.cose.service.endpoint"));
		EnvironmentUtil.setApiGeeCoseClientId(env.getProperty("apigee.cose.service.client.id"));
		EnvironmentUtil.setApiGeeCoseClientSecret(env.getProperty("apigee.cose.service.client.token"));
		
	}

	private void setEndPoints() {
		EnvironmentUtil.setIdmsEndpoint(env.getProperty("idms.endpoint"));
		EnvironmentUtil.setCoseEndpoint(env.getProperty("cose.document.service.url"));
		EnvironmentUtil.setProductRestEndpoint(env.getProperty("product.rest.service.url"));
		EnvironmentUtil.setDocumentRestEndpoint(env.getProperty("document.rest.service.url"));
		EnvironmentUtil.setGuidedSearchRestEndpoint(env.getProperty("guided.search.rest.service.url"));
	}
	private void setRestTimeOut() {
		EnvironmentUtil.setApiGeeRequestConnectTimeout(env.getProperty("apigee.request.connect.timeout"));
		EnvironmentUtil.setApiGeeRequestReadTimeout(env.getProperty("apigee.request.read.timeout"));
		EnvironmentUtil.setSalesForceRequestConnectTimeout(env.getProperty("salesforce.request.connect.timeout"));
		EnvironmentUtil.setSalesForceRequestReadTimeout(env.getProperty("salesforce.request.read.timeout"));
		EnvironmentUtil.setIdmsRequestConnectTimeout(env.getProperty("idms.request.connect.timeout"));
		EnvironmentUtil.setIdmsRequestReadTimeout(env.getProperty("idms.request.read.timeout"));
		EnvironmentUtil.setCoseRequestConnectTimeout(env.getProperty("cose.document.service.connect.timeout"));
		EnvironmentUtil.setCoseRequestReadTimeout(env.getProperty("cose.document.service.read.timeout"));
	}
}
