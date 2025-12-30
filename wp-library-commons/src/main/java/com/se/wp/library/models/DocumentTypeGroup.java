package com.se.wp.library.models;

public class DocumentTypeGroup extends DataToDisplay {

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Object o) {
		DocumentTypeGroup dtg = (DocumentTypeGroup) o;
		return this.getLabel().compareTo(dtg.getLabel());
	}

	// Overriding equals() to compare two DocumentTypeGroup objects
	@Override
	public boolean equals(Object o) {

		// If the object is compared with itself then return true
		if (o == this) {
			return true;
		}

		/*
		 * Check if o is an instance of DocumentTypeGroup or not
		 * "null instanceof [type]" also returns false
		 */
		if (!(o instanceof DocumentTypeGroup)) {
			return false;
		}

		// typecast o to DocumentTypeGroup so that we can compare data members
		DocumentTypeGroup d = (DocumentTypeGroup) o;

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
