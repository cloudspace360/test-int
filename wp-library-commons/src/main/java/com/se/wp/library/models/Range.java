package com.se.wp.library.models;

public class Range extends DataToDisplay {
	private boolean legacy;

	/**
	 * 
	 */
	public Range() {
		super();
		legacy = false;
	}

	/**
	 * @return
	 */
	public boolean isLegacy() {
		return legacy;
	}

	/**
	 * @param legacy
	 */
	public void setLegacy(boolean legacy) {
		this.legacy = legacy;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Object o) {
		Range r = (Range) o;
		return this.getLabel().compareTo(r.getLabel());
	}

	// Overriding equals() to compare two Range objects
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
		 * Check if o is an instance of Range or not "null instanceof [type]"
		 * also returns false
		 */
		if (!(o instanceof Range)) {
			return false;
		}

		// typecast o to Range so that we can compare data members
		Range r = (Range) o;

		/*
		 * Compare the data members and return accordingly return
		 */
		return Long.compare(id, r.id) == 0;
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
