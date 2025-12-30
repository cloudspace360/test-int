package com.se.wp.library.utils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import jakarta.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.se.wp.library.constants.Constants;
@Component
public class DsuUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(ConfigUtil.class);
	@Autowired
	EnvironmentUtil environmentUtil;
	@Autowired
	PropertiesUtil propertiesUtil;

	public void addSecurityHeaders(HttpServletResponse response)throws IOException  {
		String dsuSecurityConfig = propertiesUtil.getProperty(Constants.SECURITY_CONFIG_NAME,
				"ddp.security.header.config.name");
		if (dsuSecurityConfig == null || dsuSecurityConfig.isEmpty()) {
			dsuSecurityConfig = Constants.SECURITY_CONFIG_NAME;
		}
		String csp=propertiesUtil.getProperty(dsuSecurityConfig, "dsu.header.contents.security.policy");
		String fp=propertiesUtil.getProperty(dsuSecurityConfig, "dsu.header.feature.security.policy");
		String rp=propertiesUtil.getProperty(dsuSecurityConfig, "dsu.header.referrer.security.policy");
		String acao=propertiesUtil.getProperty(dsuSecurityConfig, "dsu.header.access.control.allow.origin");
		String sts=propertiesUtil.getProperty(dsuSecurityConfig, "dsu.header.strict.transport.security");
		if (csp!=null && !csp.isEmpty()) {
			response.addHeader(Constants.CONTENT_SECURITY_POLICY,csp);
		}
		if (fp!=null && !fp.isEmpty()) {
			response.addHeader(Constants.FEATURE_POLICY,fp);
		}
		if (rp!=null && !rp.isEmpty()) {
			response.addHeader(Constants.REFERRER_POLICY,rp);
		}
		if (acao!=null && !acao.isEmpty()) {
			response.addHeader(Constants.ACCESS_CONTROL_ALLOW_ORIGIN,acao);
		}
		if (sts!=null && !sts.isEmpty()) {
			response.addHeader(Constants.STRICT_TRANSPORT_SECURITY,sts);
		}
		String xContentTypeOptions=propertiesUtil.getProperty(dsuSecurityConfig, "dsu.header.x.content.type.options");
		String xFrameOptions=propertiesUtil.getProperty(dsuSecurityConfig, "dsu.header.x.frame.options");
		String xXssProtection=propertiesUtil.getProperty(dsuSecurityConfig, "dsu.header.x.xss.protection");
		if (xContentTypeOptions!=null && !xContentTypeOptions.isEmpty()) {
			response.addHeader(Constants.X_CONTENT_TYPE_OPTIONS,xContentTypeOptions);
		}
		if (xFrameOptions!=null && !xFrameOptions.isEmpty()) {
			response.addHeader(Constants.X_FRAME_OPTIONS,xFrameOptions);
		}
		if (xXssProtection!=null && !xXssProtection.isEmpty()) {
			response.addHeader(Constants.X_XSS_PROTECTION,xXssProtection);
		}
	}
	
	public Map<String, String> getSecurityHeaders() throws IOException {
		Map<String, String> securityHeaders = new TreeMap<>();
		String dsuSecurityConfig = propertiesUtil.getProperty(Constants.SECURITY_CONFIG_NAME,
				"ddp.security.header.config.name");
		if (dsuSecurityConfig == null || dsuSecurityConfig.isEmpty()) {
			dsuSecurityConfig = Constants.SECURITY_CONFIG_NAME;
		}
		String csp = propertiesUtil.getProperty(dsuSecurityConfig, "dsu.header.contents.security.policy");
		String fp = propertiesUtil.getProperty(dsuSecurityConfig, "dsu.header.feature.security.policy");
		String rp = propertiesUtil.getProperty(dsuSecurityConfig, "dsu.header.referrer.security.policy");
		String acao = propertiesUtil.getProperty(dsuSecurityConfig, "dsu.header.access.control.allow.origin");
		String sts = propertiesUtil.getProperty(dsuSecurityConfig, "dsu.header.strict.transport.security");
		if (csp != null && !csp.isEmpty()) {
			securityHeaders.put(Constants.CONTENT_SECURITY_POLICY, csp);
		}
		if (fp != null && !fp.isEmpty()) {
			securityHeaders.put(Constants.FEATURE_POLICY, fp);
		}
		if (rp != null && !rp.isEmpty()) {
			securityHeaders.put(Constants.REFERRER_POLICY, rp);
		}
		if (acao != null && !acao.isEmpty()) {
			securityHeaders.put(Constants.ACCESS_CONTROL_ALLOW_ORIGIN, acao);
		}
		if (sts != null && !sts.isEmpty()) {
			securityHeaders.put(Constants.STRICT_TRANSPORT_SECURITY, sts);
		}

		String xContentTypeOptions = propertiesUtil.getProperty(dsuSecurityConfig,
				"dsu.header.x.content.type.options");
		String xFrameOptions = propertiesUtil.getProperty(dsuSecurityConfig, "dsu.header.x.frame.options");
		String xXssProtection = propertiesUtil.getProperty(dsuSecurityConfig, "dsu.header.x.xss.protection");
		if (xContentTypeOptions != null && !xContentTypeOptions.isEmpty()) {
			securityHeaders.put(Constants.X_CONTENT_TYPE_OPTIONS, xContentTypeOptions);
		}
		if (xFrameOptions != null && !xFrameOptions.isEmpty()) {
			securityHeaders.put(Constants.X_FRAME_OPTIONS, xFrameOptions);
		}
		if (xXssProtection != null && !xXssProtection.isEmpty()) {
			securityHeaders.put(Constants.X_XSS_PROTECTION, xXssProtection);
		}
		return securityHeaders;
	}
	@Cacheable(value = Constants.PUBLIC_API_GEE_CONSUMER_CACHE_NAME)
	public boolean isApiGeeConsumer() throws IOException {
		boolean isApiGeeConsumer = false;
			if (Boolean.parseBoolean(
				propertiesUtil.getProperty(Constants.GLOBAL_CONFIG_FILE_NAME, Constants.BSL_APIGEE_ENABLED))
				) {
			LOGGER.debug("APIGEE consumer...");
			isApiGeeConsumer=true;
	}
		return isApiGeeConsumer;
	}


public void setTimeout(String connectTimeOut, String readTimeOut, RestTemplate restTemplate) {
	LOGGER.debug("connectTimeOut: {}", connectTimeOut);
	LOGGER.debug("readTimeOut: {}", readTimeOut);
	SimpleClientHttpRequestFactory rf = (SimpleClientHttpRequestFactory) restTemplate.getRequestFactory();
	if (connectTimeOut != null && !connectTimeOut.isEmpty()) {
		try {
			rf.setConnectTimeout(Integer.valueOf(connectTimeOut));
		} catch (NumberFormatException e) {
			LOGGER.error("Error parsing connectTimeout...");
		}
	}
	if (readTimeOut != null && !readTimeOut.isEmpty()) {
		try {
			rf.setReadTimeout(Integer.valueOf(readTimeOut));
		} catch (NumberFormatException e) {
			LOGGER.error("Error parsing readTimeout...");
		}
	}
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
	if(data!=null && !data.isEmpty()){
		parsedData = Jsoup.parse(data).text();
	}
	return parsedData;
}
}