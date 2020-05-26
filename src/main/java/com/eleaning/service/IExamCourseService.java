package com.eleaning.service;

import java.util.List;

import com.eleaning.entity.ExamCourseEntity;

public interface IExamCourseService {
	public ExamCourseEntity save(ExamCourseEntity examCourse);
	public List<ExamCourseEntity> getAll();
	public List<ExamCourseEntity> getByElementCourseId(Long id);
	public boolean delete(Long id);
	public ExamCourseEntity findById(Long id);
}
