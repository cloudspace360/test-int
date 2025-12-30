package com.se.wp.library.models;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
public class DocumentTypeTest {

	@InjectMocks
	DocumentType documentType;
	
	@BeforeEach
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		documentType.setName("TEST");
		documentType.setEnglishLabel("TEST");
		
		assertEquals("TEST",documentType.getName());
		assertEquals("TEST",documentType.getEnglishLabel());
	}
	
	@Test
	public void testCompareToTest1() {
		documentType.setLabel("Test");
		assertEquals(0,documentType.compareTo(documentType));
	}
	
	
	@Test
	public void testInstanceOfTest()
	{
		 Object obj = new DocumentType();
		 boolean value=obj instanceof DocumentType;
		 assertEquals(true,value);
	
	}
	
	@Test
	public void testEqualsTest1() {
		documentType.setId(1000L);
		documentType.setName("TEST");
		 Object obj = new Object();
		 assertNotEquals(documentType, obj);
		
	}
	
	@Test
	public void testEqualsTest2() {
		documentType.setId(1000L);
		documentType.setName("TESTtitle");
		assertEquals(documentType, documentType);
		assertEquals(1000, documentType.hashCode());
	}
	
	

}
