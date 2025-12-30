package com.se.wp.library.models;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class GADownloadDataLayerTest {
		
	@InjectMocks
	GADownloadDataLayer gADownloadDataLayer;
	
	private static final String TEST="test";
	
	@Test
	public void test() {
		gADownloadDataLayer.setDocumentId(TEST);
		gADownloadDataLayer.setDocumentName(TEST);
		gADownloadDataLayer.setDocumentType(TEST);
		gADownloadDataLayer.setEvent(TEST);
		
		assertEquals(TEST,gADownloadDataLayer.getDocumentId());
		assertEquals(TEST,gADownloadDataLayer.getDocumentName());
		assertEquals(TEST,gADownloadDataLayer.getDocumentType());
		assertEquals(TEST,gADownloadDataLayer.getEvent());
		
	}

}
