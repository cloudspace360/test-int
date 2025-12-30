package com.se.wp.library.models;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class DocumentTypeGroupTest {

	@InjectMocks
	DocumentTypeGroup documentTypeGroup;

	@Test
	public void testCompareToTest1() {
		documentTypeGroup.setLabel("Test");
		documentTypeGroup.setId(1000L);
	assertEquals(0,documentTypeGroup.compareTo(documentTypeGroup));
	}
	
	@Test
	public void testEqualsTest1() {
		documentTypeGroup.setId(100);;
		documentTypeGroup.setLabel("TestLabel");
		 Object obj = new Object();
		 assertNotEquals(documentTypeGroup, obj);
		
	}
	
	@Test
	public void testEqualsTest2() {
		documentTypeGroup.setId(100);;
		documentTypeGroup.setLabel("TestLabel");
		assertEquals(documentTypeGroup, documentTypeGroup);
		assertEquals(100, documentTypeGroup.hashCode());
		
		
	}

}
