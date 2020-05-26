package com.eleaning.api;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eleaning.bean.ExamCourseBean;
import com.eleaning.bean.ResponseBean;
import com.eleaning.bean.RoleNameBean;
import com.eleaning.conveter.ExamCourseConverter;
import com.eleaning.entity.CourseEntity;
import com.eleaning.entity.ExamCourseEntity;
import com.eleaning.service.ICourseService;
import com.eleaning.service.IExamCourseService;
import com.eleaning.service.IUserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/examcourses")
public class ExamCourseRestAPI {

	private static final Logger logger = LoggerFactory.getLogger(ExamCourseRestAPI.class);

	@Autowired
	private IExamCourseService examCourseService;

	@Autowired
	private ExamCourseConverter examCourseConverter;

	@Autowired
	ICourseService courseService;

	@Autowired
	private IUserService userService;

	public boolean checkRole(List list) {
		for (int i = 0; i < list.size(); i++) {
			String role = String.valueOf(list.get(i));
			RoleNameBean strRole = RoleNameBean.ROLE_STUDENT;
			String roleStudent = strRole.getValue();
			if (role.equals(roleStudent))
				return false;
		}
		return true;
	}

	@GetMapping("")
	private ResponseEntity<ResponseBean> getExamCourse(HttpServletRequest request) {
		ResponseBean responseBean = new ResponseBean();
		try {
			List<ExamCourseEntity> examCourses = examCourseService.getAll();
			if (examCourses != null) {
				responseBean.setData(examCourses);
				responseBean.setSuccess();
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
	}
	
	@GetMapping("/course/{idCourse}")
	private ResponseEntity<ResponseBean> getExamCourseId(@PathVariable Long idCourse,HttpServletRequest request) {
		ResponseBean responseBean = new ResponseBean();
		try {
			List<ExamCourseEntity> examCourses = examCourseService.getByElementCourseId(idCourse);
			if (examCourses != null) {
				responseBean.setData(examCourses);
				responseBean.setSuccess();
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
	}

	@PostMapping("/{idCourse}")
	private ResponseEntity<ResponseBean> addExamCourse(@PathVariable Long idCourse,
			@RequestBody ExamCourseBean examCourseBean, HttpServletRequest request) {
		String authHeader = request.getHeader("Authorization");
		ResponseBean responseBean = new ResponseBean();

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		List list = (List) authentication.getAuthorities();
		boolean check = checkRole(list);
		if (!check) {
			responseBean.setRoleFail();
			return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.BAD_REQUEST);
		}
		try {
			ExamCourseEntity examCourseEntity = examCourseConverter.convertEntity(examCourseBean);
			if (examCourseBean.getQuestion() == null || examCourseBean.getAnswerfirst() == null
					|| examCourseBean.getAnswersecond() == null || examCourseBean.getAnswerthird() == null
					|| examCourseBean.getAnswerfourth() == null || examCourseBean.getCorrectanswer() == null) {
				responseBean.setEnterAllRequiredFields();
				return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.BAD_REQUEST);
			}
			if (examCourseEntity != null) {
				
				CourseEntity course = courseService.findById(idCourse);
				if(course == null ) {
					responseBean.setIdObjectNotFound();
					return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.BAD_REQUEST);
				}
				examCourseEntity.setCourse_ex(course);
				ExamCourseEntity examCourse = examCourseService.save(examCourseEntity);
				responseBean.setData(examCourse);
				responseBean.setSuccess();
			}
			return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.BAD_REQUEST);
	}

	@PutMapping("/{idExamCourse}/course/{idCourse}")
	private ResponseEntity<ResponseBean> updateExamCourse(@PathVariable("idExamCourse") Long idExamCourse,
			@PathVariable("idCourse") Long idCourse, @RequestBody ExamCourseBean examCourseBean ,
			HttpServletRequest request) {
		ResponseBean responseBean = new ResponseBean();

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		List list = (List) authentication.getAuthorities();
		boolean check = checkRole(list);

		if (!check) {
			responseBean.setRoleFail();
			return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
		}

		if (examCourseBean.getQuestion() == null || examCourseBean.getAnswerfirst() == null
				|| examCourseBean.getAnswersecond() == null || examCourseBean.getAnswerthird() == null
				|| examCourseBean.getAnswerfourth() == null || examCourseBean.getCorrectanswer() == null) {
			responseBean.setEnterAllRequiredFields();
			return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.BAD_REQUEST);
		}
		try {
			ExamCourseEntity examCourseEntity = examCourseService.findById(idExamCourse);
			if (examCourseEntity != null) {
				examCourseEntity.setQuestion(examCourseBean.getQuestion());;
				examCourseEntity.setAnswerfirst(examCourseBean.getAnswerfirst());
				examCourseEntity.setAnswersecond(examCourseBean.getAnswersecond());
				examCourseEntity.setAnswerthird(examCourseBean.getAnswerthird());
				examCourseEntity.setAnswerfourth(examCourseBean.getAnswerfourth());
				examCourseEntity.setCorrectanswer(examCourseBean.getCorrectanswer());
				CourseEntity course = courseService.findById(idCourse);
				examCourseEntity.setCourse_ex(course);
				
				ExamCourseEntity examCourse = examCourseService.save(examCourseEntity);
				responseBean.setData(examCourse);
				responseBean.setSuccess();
			}
			return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("/{id}")
	private ResponseEntity<ResponseBean> deleteExamCourse(@PathVariable("id") Long id,HttpServletRequest request){
		ResponseBean responseBean = new ResponseBean();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		List list = (List) authentication.getAuthorities();
		boolean check = checkRole(list);
		
		if(!check) {
			responseBean.setRoleFail();
			return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.BAD_REQUEST);
		}
		try {
			boolean result = examCourseService.delete(id);
			if(result) {
				responseBean.setData("{}");
				responseBean.setSuccess();
			}else {
				responseBean.setDeleteFail();
				return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.BAD_REQUEST);
			}
			
			return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.BAD_REQUEST);
	}
}
