package com.se.wp.library.soap.bsl.util;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class BslConstantsTest {

	@InjectMocks
	BslContants bslConstants;
	
	@Test
	public void test() {
		assertEquals("bsl.document.service.url",BslContants.BSL_DOCUMENT_SERVICE_URL);
		assertEquals("docTypeGroup",BslContants.COUNT_BY_DOC_TYPE_GROUP);
		assertEquals("docType",BslContants.COUNT_BY_DOC_TYPE);
		assertEquals("range",BslContants.COUNT_BY_RANGE);
		assertEquals("0",BslContants.COUNT_PARAM_DEFAUTL);
		assertEquals("DOC_TYPE_GROUP",BslContants.COUNT_BY_FIELD_DOC_TYPE_GROUP);
		assertEquals("DOC_TYPE",BslContants.COUNT_BY_FIELD_DOC_TYPE);
		assertEquals("RANGE",BslContants.COUNT_BY_FIELD_RANGE);
		assertEquals("ALL",BslContants.COUNT_BY_FIELD_ALL);
		assertEquals("LEGACY",BslContants.LEGACY);

	}

}
