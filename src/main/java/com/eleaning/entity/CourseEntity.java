package com.eleaning.entity;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="course")
public class CourseEntity {
	@Id
	private Long id;
	
	@Size(min=3, max = 50)
	private String name;
	
	private String description;
	
	private String image;
	
	private Timestamp createddate;
	
	private Timestamp modifieddate;
	
	private String createdby;
	
	private String modifiedby;
	
	@OneToMany(
		        mappedBy = "course",fetch=FetchType.EAGER
		    )
	private List<LectureEntity> lectures;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid", referencedColumnName = "id")
	@JsonIgnore
    private UserEntity user;
	
	@OneToMany(
	        mappedBy = "course_ex")
	@JsonIgnore
	private List<ExamCourseEntity> examcourses;

	public CourseEntity(Long id, @Size(min = 3, max = 50) String name, String description, String image,
			Timestamp createddate, Timestamp modifieddate, String createdby, String modifiedby,
			List<LectureEntity> lectures, UserEntity user, List<ExamCourseEntity> examcourses) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.image = image;
		this.createddate = createddate;
		this.modifieddate = modifieddate;
		this.createdby = createdby;
		this.modifiedby = modifiedby;
		this.lectures = lectures;
		this.user = user;
		this.examcourses = examcourses;
	}



	public CourseEntity() {
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
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
	 * @return the description
	 */
	public String getDescription() {
		return description;
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
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the createddate
	 */
	public Timestamp getCreateddate() {
		return createddate;
	}

	/**
	 * @param createddate the createddate to set
	 */
	public void setCreateddate(Timestamp createddate) {
		this.createddate = createddate;
	}

	/**
	 * @return the modifieddate
	 */
	public Timestamp getModifieddate() {
		return modifieddate;
	}

	/**
	 * @param modifieddate the modifieddate to set
	 */
	public void setModifieddate(Timestamp modifieddate) {
		this.modifieddate = modifieddate;
	}

	/**
	 * @return the createdby
	 */
	public String getCreatedby() {
		return createdby;
	}

	/**
	 * @param createdby the createdby to set
	 */
	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	/**
	 * @return the modifiedby
	 */
	public String getModifiedby() {
		return modifiedby;
	}

	/**
	 * @param modifiedby the modifiedby to set
	 */
	public void setModifiedby(String modifiedby) {
		this.modifiedby = modifiedby;
	}

	/**
	 * @return the lectures
	 */
	public List<LectureEntity> getLectures() {
		return lectures;
	}

	/**
	 * @param lectures the lectures to set
	 */
	public void setLectures(List<LectureEntity> lectures) {
		this.lectures = lectures;
	}

	/**
	 * @return the user
	 */
	public UserEntity getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(UserEntity user) {
		this.user = user;
	}

	/**
	 * @return the examcourses
	 */
	public List<ExamCourseEntity> getExamcourses() {
		return examcourses;
	}

	/**
	 * @param examcourses the examcourses to set
	 */
	public void setExamcourses(List<ExamCourseEntity> examcourses) {
		this.examcourses = examcourses;
	}
	
	
}