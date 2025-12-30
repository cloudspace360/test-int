package com.se.wp.library.models;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
public class DocumentFileTest{
	@InjectMocks
	DocumentFile documentFile;

	@Test
	public void testDocumentFileTest() {
		documentFile.setExtension(".PDF");
		documentFile.setFilename("TESTFILE");
		documentFile.setDownloadFileURL("TESTURL");
		documentFile.setDisableDownload(true);
		documentFile.setId("100");
		documentFile.setSize("10MB");
		
		assertEquals(".PDF",documentFile.getExtension());
		assertEquals("TESTFILE",documentFile.getFilename());
		assertEquals("TESTURL",documentFile.getDownloadFileURL());
		assertEquals(true,documentFile.isDisableDownload());
		assertEquals("100",documentFile.getId());
		assertEquals("10MB",documentFile.getSize());
	}

}
