package com.eleaning.api;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestAPI {

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
