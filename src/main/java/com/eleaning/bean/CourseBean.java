package com.eleaning.bean;

import javax.validation.constraints.Size;

public class CourseBean {
	@Size(min = 3, max = 50)
	private String name;

	private String image;

	private String description;

	private boolean isActive;

	public CourseBean(@Size(min = 3, max = 50) String name, String image, String description, boolean isActive) {
		this.name = name;
		this.image = image;
		this.description = description;
		this.isActive = isActive;
	}

	public CourseBean() {
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * @param image the image to set
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * @return the isActive
	 */
	public boolean isActive() {
		return isActive;
	}

	/**
	 * @param isActive the isActive to set
	 */
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	
}
