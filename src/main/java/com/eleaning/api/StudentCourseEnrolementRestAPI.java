package com.eleaning.api;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eleaning.bean.CourseBean;
import com.eleaning.bean.MapBean;
import com.eleaning.bean.ResponseBean;
import com.eleaning.bean.RoleNameBean;
import com.eleaning.conveter.CourseConverter;
import com.eleaning.entity.CourseEntity;
import com.eleaning.entity.RoleEntity;
import com.eleaning.entity.UserEntity;
import com.eleaning.service.ICourseService;
import com.eleaning.service.IUserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/enrolements")
public class StudentCourseEnrolementRestAPI {

	private static final Logger logger = LoggerFactory.getLogger(StudentCourseEnrolementRestAPI.class);
	private static final String[] roleNoAccess = {"ROLE_ADMIN","ROLE_TEACHER"};
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private ICourseService courseService;
	
	@Autowired
	private CourseConverter courseConverter;
	
	public boolean checkRole(List list) {
		for (int i = 0; i < list.size(); i++) {
			String role = String.valueOf(list.get(i));
			RoleNameBean strRole = RoleNameBean.ROLE_STUDENT;
			String roleStudent = strRole.getValue();
			if(role.equals(roleStudent))
				return true;
		}
		return false;
	}
	
	@PostMapping("/users/{userid}/courses/{courseid}")
	public ResponseEntity<ResponseBean> enrolementStudent(@PathVariable Long userid, @PathVariable Long courseid){
		ResponseBean responseBean = new ResponseBean();
		MapBean mapBean = new MapBean();
		try {			
			Set<CourseEntity> courses = new HashSet<CourseEntity>();
			boolean flag=false;
			UserEntity user = userService.findUserByid(userid);
			Iterable<RoleEntity> role = user.getRole();
			for(String roleNoAccess: roleNoAccess) {
				if(roleNoAccess.equals(String.valueOf(role.iterator().next().getRolename()))) {
					flag=true;
					break;
				}
			}
			if(flag) {
				responseBean.setRoleFail();
				return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.BAD_REQUEST);
			}else {
				CourseEntity course = courseService.findById(courseid);
				CourseBean courseBean = courseConverter.convertBean(course);
				courses.add(course);
				user.setCourser_enroll(courses);
				userService.save(user);
				
				mapBean.put("username", user.getUsername());
				mapBean.put("email", user.getEmail());
				mapBean.put("fullname", user.getFullname());
				mapBean.put("course",courseBean);
				
				responseBean.setData(mapBean);
				responseBean.setSuccess();
				return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.OK);
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage());
			responseBean.setBadRequest();
			return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.OK);
		}
	}
}
