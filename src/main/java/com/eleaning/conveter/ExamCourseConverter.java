package com.eleaning.conveter;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.eleaning.bean.ExamCourseBean;
import com.eleaning.entity.ExamCourseEntity;

@Component
public class ExamCourseConverter {
	public ExamCourseBean convertBean(ExamCourseEntity entity) {
		ModelMapper modelMapper = new ModelMapper();
		ExamCourseBean result =modelMapper.map(entity, ExamCourseBean.class);
		return result;
	}
	
	public ExamCourseEntity convertEntity(ExamCourseBean bean) {
		ModelMapper modelMapper = new ModelMapper();
		ExamCourseEntity result = modelMapper.map(bean, ExamCourseEntity.class);
		return result;
	}
}
