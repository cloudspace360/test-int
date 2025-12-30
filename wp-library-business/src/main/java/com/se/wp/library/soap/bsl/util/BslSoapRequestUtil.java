
package com.se.wp.library.soap.bsl.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.se.wp.library.constants.Constants;
import com.se.wp.library.utils.EnvironmentUtil;
import com.se.wp.library.utils.PropertiesUtil;

@Component
public class BslSoapRequestUtil {
	@Autowired
	PropertiesUtil propertiesUtil;
	@Autowired
	EnvironmentUtil environmentUtil;

	public com.schneider.oreo.service.document.LocaleBean getDocumentServiceLocaleBean() {
		com.schneider.oreo.service.document.LocaleBean localeBean = new com.schneider.oreo.service.document.LocaleBean();
		String bslLocale = environmentUtil.getBslLocale();
		if (bslLocale != null && !bslLocale.isEmpty() && bslLocale.contains(Constants.UNDERSCORE)) {
			localeBean.setIsoCountry(bslLocale.split(Constants.UNDERSCORE)[1]);
			localeBean.setIsoLanguage(bslLocale.split(Constants.UNDERSCORE)[0]);
		}
		return localeBean;
	}

	public com.schneider.oreo.service.document.ScopeBean getDocumentServiceScopeBean(){
		com.schneider.oreo.service.document.ScopeBean scope = new com.schneider.oreo.service.document.ScopeBean();
		scope.setBrand(environmentUtil.getBslScopeBrand());
		scope.setCountry(environmentUtil.getBslScopeCountry());
		scope.setProject(environmentUtil.getBslScopeProject());
		return scope;
	}

}
