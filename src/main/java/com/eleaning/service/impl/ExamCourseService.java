package com.eleaning.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eleaning.entity.ExamCourseEntity;
import com.eleaning.repository.IExamCourseRepository;
import com.eleaning.service.IExamCourseService;

@Service
public class ExamCourseService implements IExamCourseService {

	@Autowired
	private IExamCourseRepository examCourseRepository;

	@Override
	public ExamCourseEntity save(ExamCourseEntity examCourse) {
		try {
			if (examCourse.getId() == null) {
				examCourse.setId(Calendar.getInstance().getTimeInMillis());
				examCourse.setCreateddate(new Timestamp(System.currentTimeMillis()));
			} else {
				examCourse.setModifieddate(new Timestamp(System.currentTimeMillis()));
			}
			examCourseRepository.save(examCourse);
			return examCourse;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<ExamCourseEntity> getAll() {
		try {
			List<ExamCourseEntity> result = new ArrayList<ExamCourseEntity>();
			examCourseRepository.findAll().forEach(result::add);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<ExamCourseEntity>();
	}

	@Override
	public boolean delete(Long id) {
		try {
			examCourseRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public ExamCourseEntity findById(Long id) {
		try {
			Optional<ExamCourseEntity> data = examCourseRepository.findById(id);
			if(data.isPresent()) {
				return data.get();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ExamCourseEntity();
	}

	@Override
	public List<ExamCourseEntity> getByElementCourseId(Long id) {
		try {
			List<ExamCourseEntity> listData = examCourseRepository.findByCourseIdAll(id);
			return listData;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
