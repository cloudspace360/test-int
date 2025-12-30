
package com.se.wp.library.soap.bsl.rest.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import com.schneider.rest.document.details.service.responses.AttType;
import com.schneider.rest.document.service.requests.Display;
import com.schneider.rest.document.service.requests.DisplayBean;
import com.schneider.rest.document.service.requests.Pagination;
import com.schneider.rest.document.service.requests.Query;
import com.schneider.rest.document.service.requests.QueryBean;
import com.schneider.rest.product.service.requests.Locale;
import com.schneider.rest.product.service.requests.Scope;
import com.se.wp.library.constants.Constants;
import com.se.wp.library.soap.bsl.rest.util.BslContantsRest;
import com.se.wp.library.soap.bsl.util.BslContants;
import com.se.wp.library.utils.DsuUtil;
import com.se.wp.library.utils.EnvironmentUtil;
import com.se.wp.library.utils.PropertiesUtil;

@Component
public class BslSoapRequestRestUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(BslSoapRequestRestUtil.class);

	@Autowired
	PropertiesUtil propertiesUtil;
	@Autowired
	EnvironmentUtil environmentUtil;
//	public Locale getProductServiceLocale()
//			throws IOException {
//		String bslLocaleParam = environmentUtil.getBslLocale();
//		Locale locale= new Locale();
//		if (bslLocaleParam != null && !bslLocaleParam.isEmpty() && bslLocaleParam.contains(Constants.UNDERSCORE)) {
//			locale.setIsoCountry(bslLocaleParam.split(Constants.UNDERSCORE)[1]);
//			locale.setIsoLanguage(bslLocaleParam.split(Constants.UNDERSCORE)[0]);
//		}
//		return locale;
//	}
    public List<Locale> getProductServiceLocale() throws IOException {
        String bslLocaleParam = environmentUtil.getBslLocale();
        List<Locale> locales = new ArrayList<>();

        if (bslLocaleParam != null && !bslLocaleParam.isEmpty() && bslLocaleParam.contains(Constants.UNDERSCORE)) {
            String[] localeParams = bslLocaleParam.split(Constants.UNDERSCORE);
            Locale locale = new Locale();
            locale.setIsoCountry(localeParams[1]);
            locale.setIsoLanguage(localeParams[0]);
            locales.add(locale);
        }
        return locales;
    }
	
	public Scope getProductServiceScope() throws IOException {
		Scope scope = new Scope();
		setScopeParam(scope, Constants.BSL_SCOPE_PARAM_BRAND, environmentUtil.getBslScopeBrand());
		setScopeParam(scope, Constants.BSL_SCOPE_PARAM_COUNTRY,environmentUtil.getBslScopeCountry());
		setScopeParam(scope, Constants.BSL_SCOPE_PARAM_PROJECT, environmentUtil.getBslScopeProject());
		return scope;
	}

	protected void setScopeParam(Scope scope, String paramName, String paramValue) {
		if (paramValue != null && !paramValue.isEmpty())
			switch (paramName) {
			case Constants.BSL_SCOPE_PARAM_BRAND:
				scope.setBrand(paramValue);
				break;
			case Constants.BSL_SCOPE_PARAM_COUNTRY:
				scope.setCountry(paramValue);
				break;
			case Constants.BSL_SCOPE_PARAM_PROJECT:
				scope.setProject(paramValue);
				break;
			}
	}
	public Query getQuery(String searchString, Map<String, List<Long>> numericFilters,
			Map<String, List<String>> stringFiletrs) {
		LOGGER.debug("BslDocumentServiceRequestUtil.getQueryBean()...");
		Query query = new Query();
		if (searchString != null && !searchString.isEmpty()) {
			query.setSearchString(searchString);
		}
		setNumericFilters(numericFilters, query);
		setStringFilters(stringFiletrs, query);
		return query;
	}
	protected void setStringFilters(Map<String, List<String>> stringFiletrs, Query query) {
		if (stringFiletrs != null && !stringFiletrs.isEmpty()) {
			LOGGER.debug("String filters available: {}", stringFiletrs);
			Collection<String> refNums = stringFiletrs.get(Constants.REF_NUM);
			if (refNums != null && !refNums.isEmpty()) {
				AttType attType;
				for (String refNum : refNums) {
					if (refNum != null && !refNum.isEmpty()) {
						attType = new AttType();
						attType.setName("Refnum");
						attType.setValue(refNum);
						query.getAttType().add(attType);
					}
				
				}
			}
		}
	}
	protected void setDocStringFiletrs(Map<String, List<String>> stringFiletrs, Query query) {
		Collection<String> docReferences = stringFiletrs.get(BslContants.DOC_REFERENCE);
		if (docReferences != null && !docReferences.isEmpty()) {
			LOGGER.debug("docReferences {}", docReferences);
			QueryBean queryBean;
			for (String docReference : docReferences) {
				if (docReference != null && !docReference.isEmpty()) {
					queryBean = new QueryBean();
					queryBean.setName(BslContants.DOC_REFERENCE);
					queryBean.setValue(docReference);
					query.getQueryBean().add(queryBean);
				}
				
			}}
		}
		protected void setNumericFilters(Map<String, List<Long>> numericFilters, Query query) {
			if (numericFilters != null && !numericFilters.isEmpty()) {
				LOGGER.debug("Numeric filters available: {}", numericFilters);
				List<Long> docTypeFilter = numericFilters.get(Constants.DOC_TYPE);
				if (docTypeFilter != null && !docTypeFilter.isEmpty()) {
					LOGGER.debug("doc type filter availabel...");
					query.getDocTypes().addAll(docTypeFilter);
				}

			}
		}
		public Pagination getPagination(int itemsPerPage, int pageNumber) {
			Pagination pagination = new Pagination();
			pagination.setFirstResult(String.valueOf((pageNumber-1) * itemsPerPage));
			pagination.setMaxResult(String.valueOf(itemsPerPage));
			return pagination;
		}

}