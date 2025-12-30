package com.se.wp.library.soap.bsl.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.schneider.oreo.service.document.AttType;
import com.schneider.oreo.service.document.DocCountByBean;
import com.schneider.oreo.service.document.DocCountByFieldBean;
import com.schneider.oreo.service.document.PaginationBean;
import com.schneider.oreo.service.document.QueryBean;
import com.se.wp.library.constants.Constants;

@Component
public class BslDocumentServiceRequestUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(BslDocumentServiceRequestUtil.class);

	public QueryBean getQueryBean(String searchString, Map<String, List<Long>> numericFilters,
			Map<String, List<String>> stringFiletrs) {
		LOGGER.debug("BslDocumentServiceRequestUtil.getQueryBean()...");
		QueryBean queryBean = new QueryBean();
		if (searchString != null && !searchString.isEmpty()) {
			queryBean.setSearchString(searchString);
		}
		setNumericFilters(numericFilters, queryBean);
		setStringFilters(stringFiletrs, queryBean);
		return queryBean;
	}

	protected void setStringFilters(Map<String, List<String>> stringFiletrs, QueryBean queryBean) {
		if (stringFiletrs != null && !stringFiletrs.isEmpty()) {
			LOGGER.debug("stringFiletrs: {}", stringFiletrs);
			List<String> refNums = stringFiletrs.get(Constants.REF_NUM);
			if (refNums != null && !refNums.isEmpty()) {
				Collection<AttType> attrs = new ArrayList<>();
				AttType attType;
				for (String refNum : refNums) {
					attType = new AttType();
					attType.setName("Refnum");
					attType.setValue(refNum);
					attrs.add(attType);
				}
				queryBean.getAttType().addAll(attrs);
			}
		}
	}

	protected void setNumericFilters(Map<String, List<Long>> numericFilters, QueryBean queryBean) {
		if (numericFilters != null && !numericFilters.isEmpty()) {
			LOGGER.debug("Numeric filters availabel...");
			LOGGER.debug("numericFilters: {}", numericFilters);
			List<Long> docTypeFilter = numericFilters.get(Constants.DOC_TYPE);
			if (docTypeFilter != null && !docTypeFilter.isEmpty()) {
				LOGGER.debug("doc type filter availabel...");
				queryBean.getDocTypes().addAll(docTypeFilter);
			}
		}
	}

	public PaginationBean getPaginationBean(int itemsPerPage, int pageNumber) {
		PaginationBean paginationBean = new PaginationBean();
		paginationBean.setFirstResult((long) (itemsPerPage * pageNumber) - itemsPerPage);
		paginationBean.setMaxResult(Long.valueOf(itemsPerPage));
		return paginationBean;
	}

	protected void setCountByBean(String countBy, String countParam, List<DocCountByBean> countByBeanList) {
		DocCountByBean countByBean = new DocCountByBean();
		countByBean.setCountBy(DocCountByFieldBean.fromValue(countBy));
		countByBean.setCountParam(countParam);
		countByBeanList.add(countByBean);
	}
}
