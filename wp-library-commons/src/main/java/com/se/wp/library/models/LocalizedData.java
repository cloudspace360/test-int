package com.se.wp.library.models;

import java.util.List;
import java.util.Map;

public class LocalizedData {
	Map<String, String> labels;
	List<String> personas;
	String disclaimer;
	Map<String, String> countries;
	Map<String, String> footerLabels;

	public Map<String, String> getLabels() {
		return labels;
	}

	public void setLabels(Map<String, String> labels) {
		this.labels = labels;
	}

	public void setPersonas(List<String> personas) {
		this.personas = personas;
	}

	public Map<String, String> getCountries() {
		return countries;
	}

	public void setCountries(Map<String, String> countries) {
		this.countries = countries;
	}

	public String getDisclaimer() {
		return disclaimer;
	}

	public void setDisclaimer(String disclaimer) {
		this.disclaimer = disclaimer;
	}

	public Map<String, String> getFooterLabels() {
		return footerLabels;
	}

	public void setFooterLabels(Map<String, String> footerLabels) {
		this.footerLabels = footerLabels;
	}

	public List<String> getPersonas() {
		return personas;
	}
}