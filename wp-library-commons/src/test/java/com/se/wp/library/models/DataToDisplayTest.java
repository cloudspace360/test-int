package com.se.wp.library.models;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class DataToDisplayTest {

	@Test
	public void testDataToDisplayTest() {

		DataToDisplay dataToDisplay = Mockito.mock(DataToDisplay.class, Mockito.CALLS_REAL_METHODS);
		dataToDisplay.setId(1000L);
		dataToDisplay.setLabel("Testlabel");
		dataToDisplay.setNumberOfDocs(1000L);
		dataToDisplay.setStatic(true);
		dataToDisplay.setChecked(true);

		assertEquals(1000L, dataToDisplay.getId());
		assertEquals("Testlabel", dataToDisplay.getLabel());
		assertEquals(Long.valueOf(1000), dataToDisplay.getNumberOfDocs());
		assertEquals(true, dataToDisplay.isStatic());
		assertEquals(true, dataToDisplay.isChecked());
		
		assertEquals("1000-Testlabel", dataToDisplay.toString());

	}

}
