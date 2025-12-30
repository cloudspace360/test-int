package com.se.wp.library.soap.bsl.util;

import com.se.wp.library.models.rest.ProductBrand;

public class BslContants {
	public static final String BSL_DOCUMENT_SERVICE_URL = "bsl.document.service.url";

	public static final String COUNT_BY_ALL = "all";
	public static final String COUNT_BY_DOC_TYPE_GROUP = "docTypeGroup";
	public static final String COUNT_BY_DOC_TYPE = "docType";
	public static final String COUNT_BY_CATEGORY = "category";
	public static final String COUNT_BY_SUBCATEGORY = "category";
	public static final String COUNT_BY_RANGE = "range";
	public static final String COUNT_BY_BRAND = "brand";
	public static final String COUNT_BY_APPLICATION = "application";
	public static final String COUNT_BY_LANGUAGE = "language";
	public static final String SCOPE_GLOBAL = "GLOBAL";
	public static final String SCOPE_DOCUMENT = "DOCUMENT";
	public static final String SCOPE_DOCTYPEGROUPE = "DOCTYPEGROUPE";
	public static final String SCOPE_DOCTYPE = "DOCTYPE";
	public static final String SCOPE_CATEGORY = "CATEGORY";
	public static final String SCOPE_SUBCATEGORY = "SUBCATEGORY";
	public static final String SCOPE_RANGE = "RANGE";
	public static final String SCOPE_PRODUCT_BRAND = "PRODUCT_BRAND";
	public static final String SCOPE_LANGUAGE = "LANGUAGE";
	public static final String SCOPE_APPLICATION = "APPLICATION";
	public static final String COUNT_PARAM_DEFAUTL = "0";
	public static final String COUNT_PARAM_ONE = "1";
	public static final String COUNT_PARAM_TWO = "2";

	public static final String COUNT_BY_FIELD_DOC_TYPE_GROUP = "DOC_TYPE_GROUP";
	public static final String COUNT_BY_FIELD_DOC_TYPE = "DOC_TYPE";
	public static final String COUNT_BY_FIELD_CATEGORY = "CATEGORY";
	public static final String COUNT_BY_FIELD_RANGE = "RANGE";
	public static final String COUNT_BY_FIELD_LANGUAGE = "LANGUAGE";
	public static final String COUNT_BY_FIELD_BRAND = "BRAND";
	public static final String COUNT_BY_FIELD_APPLICATION = "APPLICATION";
	public static final String COUNT_BY_FIELD_ALL = "ALL";

	public static final String DEFAULT_SEARCH_ATTRIBUTE = "All";

	public static final int MAX_COUNT_RESULTS_ZERO = 0;
	public static final int DEFAULT_MAX_COUNT_RESULTS = 1000;
	public static final int RANGE_MAX_COUNT_RESULTS = 2000;
	public static final int MAX_COUNT_RESULTS = 1000;

	public static final String DOC_DISPLAY_BEAN_FIELD_DOC_REF = "doc_reference";

	public static final String SORT_ORDER_ASC = "asc";
	public static final String SORT_ORDER_DESC = "desc";
	public static final String SORT_BY_RELEVANCE = "Relevance";
	public static final String SORT_BY_POPULARITY = "Popularity";
	public static final String SORT_BY_DATE_NEW = "Document_Date_New";
	public static final String SORT_BY_DATE_OLD = "Document_Date_Old";
	public static final String SORT_BY_DOCUMENT_REFERENCE = "Document_Reference";
	public static final String SORT_BY_DOCUMENT_TYPE = "Document_type";
	public static final String SORT_BY_DOCUMENT_TITLE = "Document_title";

	public static final String DEFAULT_DOC_TYPE_GROUP_LABEL_PREFIX = "DocTypeGroup-";
	public static final String DOC_TYPE_GROUP_LABEL_EMPTY = "DocTypeGroup label is empty for doc type group id: ";
	public static final String DOC_TYPE_GROUP_LABEL_NULL = "DocTypeGroup label is null for doc type group id: ";
	public static final String DOC_TYPE_GROUP_NAME_TAG_MISSING = "DocTypeGroup name tag is missing for doc type group id: ";
	public static final String SLASH = "/";
	public static final String UTF_8 = "UTF-8";
	public static final String DOCUMENT_SERVICE_VERSION = null;
	public static final String LEGACY = "LEGACY";
	public static final long SILENT_LANGUAGE_ID = 1555670L;
	public static final String DOC_REFERENCE = "DOC_REFERENCE";
	
	public static final String FILTERS_WITHOUT_ID = ProductBrand.class.getName();
}
