package com.se.wp.library.constants;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
public class ConstantsTest{

	@InjectMocks
	Constants constants;
	
	@Test
	public void test() {
		assertEquals( "Internal server error.",Constants.INTERNAL_SERVER_ERROR_MESSAGE);
		assertEquals( "localizationCache",Constants.LOCALIZED_LABELS_CACHE_NAME);
		assertEquals( "&",Constants.AMPERSAND);
		assertEquals( "=",Constants.STRING_EQUALS);
		assertEquals("propertiesCache",Constants.PROPERTIES_CACHE_NAME);
		assertEquals("language-conf",Constants.GLOBAL_CONFIG_LANGUAGE_FOLDER_NAME);
		assertEquals(System.getProperty("file.separator"),Constants.FILE_SEPERATOR);
		assertEquals("utf8",Constants.UTF8);
		assertEquals("UTF_8",Constants.UTF_8);
		assertEquals("Success",Constants.SUCCESS);
		assertEquals("labels.properties",Constants.LABELS_FILE_NAME);
		assertEquals("footerLabels.properties",Constants.FOOTER_LABELS_FILE_NAME);
		assertEquals("wp-library.properties",Constants.GLOBAL_CONFIG_FILE_NAME);
		assertEquals("~",Constants.COOKIE_VAL_SEPERATOR);
		assertEquals("~",Constants.LEAD_DETAIL_SEPERATOR);
		assertEquals("\n",Constants.LEAD_MODEL_SEPERATOR);
		assertEquals("params",Constants.PARAMS);
		assertEquals("documentListing",Constants.DOCUMENT_LISTING_CACHE_NAME);
		assertEquals("docType",Constants.DOC_TYPE);
		assertEquals("refNum",Constants.REF_NUM);
		assertEquals("_",Constants.UNDERSCORE);
		assertEquals("bsl.scope.param.brand",Constants.BSL_SCOPE_PARAM_BRAND);
		assertEquals("bsl.scope.param.country",Constants.BSL_SCOPE_PARAM_COUNTRY);
		assertEquals(".",Constants.DOT);
		assertEquals(150,Constants.SHORT_DESCRIPTION_MAX_LENGTH);
		assertEquals("ZIP",Constants.FILE_EXTENSION_ZIP);
		assertEquals(".zip",Constants.ARCHIVE_FILE_EXTENSION);
		assertEquals("ONLINE-CAD",Constants.ONLINE_CAD_DOCUMENT_REFERENCE_START);
		assertEquals("1555859",Constants.CAD_DOCUMENT_TYPE_IDS);
		assertEquals("640x480_iso.pdf",Constants.ONLINE_CAD_DOCUMENT_FILE_NAME_END);
		assertEquals("dd/MM/YYYY",Constants.DEFAULT_DATE_FORMAT);
		assertEquals(3600,Constants.BSL_DOCUMENT_SERVICE_CONNECT_TIME_OUT);
		assertEquals(3600,Constants.BSL_DOCUMENT_SERVICE_READ_TIME_OUT);
		assertEquals("index",Constants.INDEX_VIEW);
		assertEquals("warning",Constants.WARNING_VIEW);
		assertEquals("*",Constants.SILENT_LANG_CODE);
		assertEquals("wp-library-conf",Constants.GLOBAL_CONFIG_FOLDER_NAME);
		
	}
}
