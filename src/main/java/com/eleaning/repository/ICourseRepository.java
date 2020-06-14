package com.eleaning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.eleaning.entity.CourseEntity;

public interface ICourseRepository extends CrudRepository<CourseEntity, Long>{

	@Query("SELECT m FROM CourseEntity m WHERE user.id = :userid ")
	List<CourseEntity> getAlByCourseId(@Param("userid") Long userid);
}
