package com.eleaning.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.eleaning.entity.ExamCourseEntity;

public interface IExamCourseRepository extends CrudRepository<ExamCourseEntity, Long>{
	@Query("SELECT m FROM ExamCourseEntity m WHERE course_ex.id = :courseid ")
	Optional<ExamCourseEntity> findByCourseId(@Param("courseid") Long courseid);
	
	@Query("SELECT m FROM ExamCourseEntity m WHERE course_ex.id = :courseid ")
	List<ExamCourseEntity> findByCourseIdAll(@Param("courseid") Long courseid);
}
