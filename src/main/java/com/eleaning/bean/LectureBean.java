package com.eleaning.bean;

import javax.validation.constraints.Size;

public class LectureBean {
	@Size(min=3, max = 50)
	private String name;
	
	public LectureBean(@Size(min = 3, max = 50) String name) {
		this.name = name;
	}

	public LectureBean() {
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


}
