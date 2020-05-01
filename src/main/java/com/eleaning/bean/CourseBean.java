package com.eleaning.bean;

import javax.validation.constraints.Size;

public class CourseBean {
	@Size(min=3, max = 50)
	private String name;
	
	private String description;
	
	public CourseBean(@Size(min = 3, max = 50) String name, String description) {
		this.name = name;
		this.description = description;
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
}
