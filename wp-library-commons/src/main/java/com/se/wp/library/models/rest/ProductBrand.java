package com.se.wp.library.models.rest;

import com.se.wp.library.models.DataToDisplay;

public class ProductBrand extends DataToDisplay {

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Object o) {
		ProductBrand b = (ProductBrand) o;
		return this.getLabel().compareTo(b.getLabel());

	}

	@Override
	public String toString() {
		return this.label;
	}
}
