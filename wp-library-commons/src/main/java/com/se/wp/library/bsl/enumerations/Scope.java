package com.se.wp.library.bsl.enumerations;

public enum Scope {

	// The values ​​are used to assess binary expressions!
	/*
	 * ATTENTION : --------- The global variable must always be set to = ( Value
	 * Scope of below * 2) -1 --------- When adding or changing a filter :
	 * ------------------we can provide add / change its name in
	 * Portal-ext.properties ( whitepaper.filter.name property)
	 * ------------------See page white -paper -filter- values.jsp because we
	 * Lists on the scope
	 */
	GLOBAL(65535), DOMAIN(32768), THEME(16384), SUBTOPIC(8192), TOPIC(4096), APPLICATION(2048), SEGMENT(1024), DOCUMENT(
			512), DOCTYPE(256), DOCTYPEGROUPE(128), COUNT(
					64), LANGUAGE(32), RANGE(16), CATEGORY(8), SUBCATEGORY(4), PRODUCT_BRAND(2), FILE_EXTENSION(1),;

	private Integer value;

	/**
	 * @param value
	 */
	Scope(Integer value) {
		this.value = value;
	}

	/**
	 * @return
	 */
	public int value() {
		return value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return value.toString();
	}

	/**
	 * @param v
	 * @return
	 */
	public static Scope fromValue(String v) {
		for (Scope c : Scope.values()) {
			if (c.value == Integer.parseInt(v)) {
				return c;
			}
		}
		throw new IllegalArgumentException(v);
	}
}
