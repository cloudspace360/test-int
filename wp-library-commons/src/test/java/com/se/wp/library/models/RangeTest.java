package com.se.wp.library.models;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
public class RangeTest  {

	@InjectMocks
	Range range;

	@BeforeEach
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		range.setLegacy(true);
		assertEquals(true,range.isLegacy());
	
	}
	@Test
	public void testCompareToTest1() {
		range.setLabel("Test");
		range.setId(1000L);
	assertEquals(0,range.compareTo(range));
	}
	
	@Test
	public void testEqualsTest1() {
		range.setId(100);;
		range.setLabel("TestLabel");
		 Object obj = new Object();
		 assertNotEquals(range, obj);
		
	}
	
	@Test
	public void testEqualsTest2() {
		range.setId(100);;
		range.setLabel("TestLabel");
		assertEquals(range, range);
		assertEquals(100, range.hashCode());
		
		
	}

}
