package com.se.wp.library.models;

public class DocumentType extends DataToDisplay {

	private String englishLabel;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return
	 */
	public String getEnglishLabel() {
		return englishLabel;
	}

	/**
	 * @param englishLabel
	 */
	public void setEnglishLabel(String englishLabel) {
		this.englishLabel = englishLabel;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Object o) {
		DocumentType d = (DocumentType) o;
		return this.getLabel().compareTo(d.getLabel());
	}

	// Overriding equals() to compare two DocumentType objects
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object o) {

		// If the object is compared with itself then return true
		if (o == this) {
			return true;
		}

		/*
		 * Check if o is an instance of DocumentType or not
		 * "null instanceof [type]" also returns false
		 */
		if (!(o instanceof DocumentType)) {
			return false;
		}

		// typecast o to DocumentType so that we can compare data members
		DocumentType d = (DocumentType) o;

		// Compare the data members and return accordingly
		return Long.compare(id, d.id) == 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return (int) ((id >> 32) ^ (id));
	}
}
