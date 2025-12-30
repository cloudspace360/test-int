package com.se.wp.library.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class LocalizedDataTest  {
	
	@InjectMocks
	LocalizedData localizedData;

	@Test
	public void test() {
		Map<String, String> countries= new HashMap<>();
		countries.put("test", "test");
		String disclaimer="test";
		Map<String, String> footerLabels= new HashMap<>();
		footerLabels.put("test", "test");
		Map<String, String> labels= new HashMap<>();
		labels.put("test", "test");
		List<String> personas=new ArrayList<>();
		personas.add("test");
		
		localizedData.setCountries(countries);
		localizedData.setDisclaimer(disclaimer);
		localizedData.setFooterLabels(footerLabels);
		localizedData.setLabels(labels);
		localizedData.setPersonas(personas);
		
		assertEquals("test",localizedData.getDisclaimer());
		assertEquals("test",localizedData.getCountries().get("test"));
		assertEquals("test",localizedData.getFooterLabels().get("test"));
		assertEquals("test",localizedData.getLabels().get("test"));
		assertEquals("test",localizedData.getPersonas().get(0));
		
	}
	
}
