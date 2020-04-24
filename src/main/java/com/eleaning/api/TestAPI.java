package com.eleaning.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eleaning.bean.ResponseBean;
import com.eleaning.entity.RoleEntity;
import com.eleaning.entity.UserEntity;
import com.eleaning.service.IUserService;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/test")
public class TestAPI {

	@Autowired
	private IUserService userService;
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseBean> test(@PathVariable long id){
		ResponseBean responseBean = new ResponseBean();
	//	List<UserEntity> users = userService.getUsers();
		UserEntity user  =  userService.findUserByid(id);
		responseBean.setData(user);
		responseBean.setSuccess();
		return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.OK);
	}
	
	@GetMapping("/api/test/student")
	@PreAuthorize("hasRole('ROLE_STUDENT')")
	public String studentAccess() {
		return ">>> User Contents!";
	}
	
	@GetMapping("/api/test/admin")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String adminAccess() {
		return ">>> ADMIN Contents!";
	}
	
	@GetMapping("/api/test/teacher")
	@PreAuthorize("hasRole('teacher')")
	public String teacherAccess() {
		return ">>> TEACHER Contents!";
	}
//	
//	@GetMapping("/api/test/user")
//	@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
//	public String userAccess() {
//		return ">>> User Contents!";
//	}
//
//	@GetMapping("/api/test/pm")
//	@PreAuthorize("hasRole('PM') or hasRole('ADMIN')")
//	public String projectManagementAccess() {
//		return ">>> Board Management Project";
//	}
//	
//	@GetMapping("/api/test/admin")
//	@PreAuthorize("hasRole('ADMIN')")
//	public String adminAccess() {
//		return ">>> Admin Contents";
//	}
}
