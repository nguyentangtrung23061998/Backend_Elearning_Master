package com.eleaning.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eleaning.entity.CourseEntity;
import com.eleaning.entity.LectureEntity;
import com.eleaning.repository.ICourseRepository;
import com.eleaning.repository.ILectureRepository;
import com.eleaning.service.ICourseService;

@Service
public class CourseService implements ICourseService{

	@Autowired
	private ICourseRepository courseRepository;
	
	@Autowired
	private ILectureRepository lectureRepository;
	
	@Override
	public CourseEntity save(CourseEntity course) {
		try {
			if(course.getId() == null) {
				course.setId(Calendar.getInstance().getTimeInMillis());
				course.setCreateddate(new Timestamp(System.currentTimeMillis()));
			}
			else{
				course.setModifieddate(new Timestamp(System.currentTimeMillis()));
			}
			courseRepository.save(course);
			return course;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<CourseEntity> getAll() {
		try {
			List<CourseEntity> result = new ArrayList<CourseEntity>();
			courseRepository.findAll().forEach(result::add);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ArrayList<CourseEntity>();
	}

	@Override
	public boolean delete(Long id) {
		try {
			Optional<LectureEntity> optionalLecture = lectureRepository.findByCourseId(id);
			LectureEntity lecture = new LectureEntity();
			if(optionalLecture.isPresent()) {
				lecture =  optionalLecture.get();
			}
			lectureRepository.delete(lecture);
			courseRepository.deleteById(id);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public CourseEntity findById(Long id) {
		try {
			Optional<CourseEntity> data = courseRepository.findById(id);
			if(data.isPresent()) {
				return data.get();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new CourseEntity();
	}

}
