package com.eleaning.bean;

public class CourseUserBean {
	private CourseBean courseBean;
	private UserBean userBean;
	
	public CourseUserBean(){}
	
	public CourseUserBean(CourseBean courseBean, UserBean userBean) {
		this.courseBean = courseBean;
		this.userBean = userBean;
	}
	
	/**
	 * @return the courseBean
	 */
	public CourseBean getCourseBean() {
		return courseBean;
	}
	/**
	 * @param courseBean the courseBean to set
	 */
	public void setCourseBean(CourseBean courseBean) {
		this.courseBean = courseBean;
	}
	/**
	 * @return the userBean
	 */
	public UserBean getUserBean() {
		return userBean;
	}
	/**
	 * @param userBean the userBean to set
	 */
	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}
	
	
}
