package com.eleaning.repository;

import org.springframework.data.repository.CrudRepository;

import com.eleaning.entity.CourseEntity;

public interface ICourseRepository extends CrudRepository<CourseEntity, Long>{

}
