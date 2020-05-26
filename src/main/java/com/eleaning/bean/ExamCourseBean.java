package com.eleaning.bean;

public class ExamCourseBean {
	private String question;

	private String answerfirst;

	private String answersecond;

	private String answerthird;

	private String answerfourth;

	private String correctanswer;

	public ExamCourseBean(String question, String answerfirst, String answersecond, String answerthird,
			String answerfourth, String correctanswer) {
		this.question = question;
		this.answerfirst = answerfirst;
		this.answersecond = answersecond;
		this.answerthird = answerthird;
		this.answerfourth = answerfourth;
		this.correctanswer = correctanswer;
	}

	public ExamCourseBean() {
	}

	/**
	 * @return the question
	 */
	public String getQuestion() {
		return question;
	}

	/**
	 * @param question the question to set
	 */
	public void setQuestion(String question) {
		this.question = question;
	}

	/**
	 * @return the answerfirst
	 */
	public String getAnswerfirst() {
		return answerfirst;
	}

	/**
	 * @param answerfirst the answerfirst to set
	 */
	public void setAnswerfirst(String answerfirst) {
		this.answerfirst = answerfirst;
	}

	/**
	 * @return the answersecond
	 */
	public String getAnswersecond() {
		return answersecond;
	}

	/**
	 * @param answersecond the answersecond to set
	 */
	public void setAnswersecond(String answersecond) {
		this.answersecond = answersecond;
	}

	/**
	 * @return the answerthird
	 */
	public String getAnswerthird() {
		return answerthird;
	}

	/**
	 * @param answerthird the answerthird to set
	 */
	public void setAnswerthird(String answerthird) {
		this.answerthird = answerthird;
	}

	/**
	 * @return the answerfourth
	 */
	public String getAnswerfourth() {
		return answerfourth;
	}

	/**
	 * @param answerfourth the answerfourth to set
	 */
	public void setAnswerfourth(String answerfourth) {
		this.answerfourth = answerfourth;
	}

	/**
	 * @return the correctanswer
	 */
	public String getCorrectanswer() {
		return correctanswer;
	}

	/**
	 * @param correctanswer the correctanswer to set
	 */
	public void setCorrectanswer(String correctanswer) {
		this.correctanswer = correctanswer;
	}
	
}
