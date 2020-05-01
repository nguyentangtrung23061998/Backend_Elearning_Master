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

import com.eleaning.bean.CourseBean;
import com.eleaning.bean.ResponseBean;
import com.eleaning.bean.RoleNameBean;
import com.eleaning.converer.CourseConverter;
import com.eleaning.entity.CourseEntity;
import com.eleaning.entity.UserEntity;
import com.eleaning.service.ICourseService;
import com.eleaning.service.IUserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/courses")
public class CourseRestAPI {
	private static final Logger logger = LoggerFactory.getLogger(CourseRestAPI.class);
	
	@Autowired
	private ICourseService courseService;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private CourseConverter courseConverter;

	public boolean checkRole(List list) {
		for (int i = 0; i < list.size(); i++) {
			String role = String.valueOf(list.get(i));
			RoleNameBean strRole = RoleNameBean.ROLE_STUDENT;
			String roleStudent = strRole.getValue();
			if(role.equals(roleStudent))
				return false;
		}
		return true;
	}
	
	@GetMapping("")
	private ResponseEntity<ResponseBean> getCourse(HttpServletRequest request){
		String authHeader = request.getHeader("Authorization");
		ResponseBean responseBean = new ResponseBean();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		try {
			List list = (List) authentication.getAuthorities();
			boolean check = checkRole(list);
			if(!check) {
				responseBean.setRoleFail();
				return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.BAD_REQUEST);
			}
			
			List<CourseEntity> courses = courseService.getAll();
			if(courses!=null) {
				responseBean.setData(courses);
				responseBean.setSuccess();
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.OK);
	}

	@PostMapping("")
	private ResponseEntity<ResponseBean> addCourse(@RequestBody CourseBean courseBean,HttpServletRequest request){
		String authHeader = request.getHeader("Authorization");
		ResponseBean responseBean = new ResponseBean();
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		List list = (List) authentication.getAuthorities();
		boolean check = checkRole(list);
		if(!check) {
			responseBean.setRoleFail();
			return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.BAD_REQUEST);
		}
		
		CourseEntity courseEntity = courseConverter.convertEntity(courseBean);
		if(courseBean.getName() == null || courseBean.getDescription() == null) {
			responseBean.setEnterAllRequiredFields();
			return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.BAD_REQUEST);
		}
		if(courseEntity!= null) {
			UserEntity user = userService.findByToken(authHeader);
			courseEntity.setUser(user);
			CourseEntity course = courseService.save(courseEntity);
			responseBean.setData(course);
			responseBean.setSuccess();
		}
		return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.OK);
	} 
	
	@PutMapping("/{id}")
	private ResponseEntity<ResponseBean> updateCourse(@PathVariable("id") Long id,@RequestBody CourseBean courseBean,HttpServletRequest request){
		ResponseBean responseBean = new ResponseBean();
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		List list = (List) authentication.getAuthorities();
		boolean check = checkRole(list);
		
		if(!check) {
			responseBean.setRoleFail();
			return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.OK);
		}
		
		if(courseBean.getName() == null || courseBean.getDescription() == null) {
			responseBean.setEnterAllRequiredFields();
			return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.BAD_REQUEST);
		}
		
		CourseEntity courseEntity = courseService.findById(id);
		if(courseEntity!= null) {
			courseEntity.setName(courseBean.getName());
			courseEntity.setDescription(courseBean.getDescription());
			CourseEntity course = courseService.save(courseEntity);
			responseBean.setData(course);
			responseBean.setSuccess();
		}
		return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.OK);
	} 
	
	@DeleteMapping("/{id}")
	private ResponseEntity<ResponseBean> deleteCourse(@PathVariable("id") Long id,HttpServletRequest request){
		ResponseBean responseBean = new ResponseBean();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		List list = (List) authentication.getAuthorities();
		boolean check = checkRole(list);
		
		if(!check) {
			responseBean.setRoleFail();
			return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.BAD_REQUEST);
		}
		
		boolean result = courseService.delete(id);
		if(result) {
			responseBean.setData("{}");
			responseBean.setSuccess();
		}else {
			responseBean.setDeleteFail();
			return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.OK);
	}
}
