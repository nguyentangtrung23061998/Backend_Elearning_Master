package com.eleaning.bean;

import javax.validation.constraints.Size;

public class LectureBean {
	@Size(min=3, max = 50)
	private String name;
	private Long courseId;
	
	public LectureBean(@Size(min = 3, max = 50) String name, Long courseId) {
		this.name = name;
		this.courseId = courseId;
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

	/**
	 * @return the courseId
	 */
	public Long getCourseId() {
		return courseId;
	}

	/**
	 * @param courseId the courseId to set
	 */
	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}

}
