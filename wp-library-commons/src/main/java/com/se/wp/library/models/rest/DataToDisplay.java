package com.se.wp.library.models.rest;

/**
 * @author ravraj.lakum DataToDisplay implements Comparable<Object> This is a
 *         POJO class for DataToDisplay bean
 */
public abstract class DataToDisplay implements Comparable<Object> {
	protected long id;
	protected String label;
	protected Long numberOfDocs;
	boolean isChecked;
	boolean isStatic;
	protected String baseName;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Long getNumberOfDocs() {
		return numberOfDocs;
	}

	public void setNumberOfDocs(Long numberOfDocs) {
		this.numberOfDocs = numberOfDocs;
	}

	public boolean isStatic() {
		return isStatic;
	}

	public void setStatic(boolean isStatic) {
		this.isStatic = isStatic;
	}

	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}
	
	public String getBaseName() {
		return baseName;
	}

	public void setBaseName(String baseName) {
		this.baseName = baseName;
	}

	@Override
	public String toString() {
		return id + "-" + label;
	}

}
