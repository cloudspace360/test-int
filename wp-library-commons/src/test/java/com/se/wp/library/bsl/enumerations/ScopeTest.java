package com.se.wp.library.bsl.enumerations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
public class ScopeTest {

	
	@BeforeEach
	public void setUp() throws Exception {
	}

	@Test
	public void testValue() {
		Scope scope=Scope.GLOBAL;
		assertEquals(65535, scope.value());
	}

	@Test
	public void testToString() {
		Scope scope=Scope.GLOBAL;
		assertEquals("65535",scope.toString());
	}

	@Test
	public void testFromValue() {
		Scope.fromValue("65535");
	}

}
