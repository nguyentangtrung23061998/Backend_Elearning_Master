package com.eleaning.api;

import java.io.IOException;
import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.eleaning.bean.CourseBean;
import com.eleaning.bean.CourseUserBean;
import com.eleaning.bean.MapBean;
import com.eleaning.bean.ResponseBean;
import com.eleaning.bean.RoleNameBean;
import com.eleaning.bean.UserBean;
import com.eleaning.conveter.CourseConverter;
import com.eleaning.conveter.UserConverter;
import com.eleaning.entity.CourseEntity;
import com.eleaning.entity.LectureEntity;
import com.eleaning.entity.UserEntity;
import com.eleaning.service.ICourseService;
import com.eleaning.service.IUserService;
import com.eleaning.util.Constant;
import com.eleaning.util.Util;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class CourseRestAPI {
	private static final Logger logger = LoggerFactory.getLogger(CourseRestAPI.class);
	
	@Autowired
	private ICourseService courseService;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private CourseConverter courseConverter;
	
	@Autowired
	private UserConverter userConverter;

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
	
	@GetMapping("/all-courses")
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
			
			CourseUserBean courseUserBean = new CourseUserBean();
			List<CourseUserBean> courseUserBeanData = new ArrayList<CourseUserBean>();
			
			for (CourseEntity courseEntity : courses) {
				UserEntity userEntity = userService.findUserByid(courseEntity.getUser().getId());
				UserBean userBean = userConverter.convertBean(userEntity);
				CourseBean courseBean  =courseConverter.convertBean(courseEntity);
				courseUserBean = new CourseUserBean(courseBean,userBean);
				courseUserBeanData.add(courseUserBean);
			}
			
			responseBean.setData(courseUserBeanData);
			responseBean.setSuccess();
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.OK);
	}
	
	@GetMapping("/courses/{id}")
	private ResponseEntity<ResponseBean> getDetailCourse(@PathVariable Long id){
		ResponseBean responseBean = new ResponseBean();
		MapBean mapBean = new MapBean();
		try {
			CourseEntity course = courseService.findById(id);
			UserEntity user = userService.findUserByid(course.getUser().getId());
			mapBean.put("course", course);
			mapBean.put("user",user);
			
			responseBean.setData(mapBean.getAll());
			responseBean.setSuccess();
			return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
			responseBean.setCouseIdNotFound();
			return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/courses")
	private ResponseEntity<ResponseBean> addCourse(@RequestBody CourseBean courseBean,HttpServletRequest request){
		String authHeader = request.getHeader("Authorization");
		System.out.println("auth :" + authHeader);
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
	
	@PostMapping("/courses/upload/{id}")
	private ResponseEntity<ResponseBean> uploadCourse(@PathVariable long id, @RequestParam("file") MultipartFile file)
			throws IOException {
		CourseEntity course = courseService.findById(id);
		ResponseBean responseBean = new ResponseBean();

		if (course != null) {
			boolean checkUpload = Util.upload(file);
			if (checkUpload) {
				String orginalFile = file.getOriginalFilename();
				String extension= orginalFile.substring(orginalFile.lastIndexOf(".") +1);
				course.setImage(orginalFile);
				
				CourseEntity courseEntity = courseService.save(course);
				responseBean.setData(courseEntity);
				responseBean.setSuccess();
				return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
			} else {
				responseBean.setFailUpload();
				return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.BAD_REQUEST);
			}
		}
		return null;
	}
	
	@PutMapping("/courses/{id}")
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
	
	@DeleteMapping("/courses{id}")
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
