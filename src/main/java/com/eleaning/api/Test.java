package com.eleaning.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eleaning.entity.UserEntity;
import com.eleaning.exception.ResourceNotFoundException;
import com.eleaning.security.CurrentUser;
import com.eleaning.security.services.UserPrinciple;
import com.eleaning.service.IUserService;

@RestController
public class Test {
	
	@Autowired
	private IUserService userService;
	
	@GetMapping("/user/me")
	public UserEntity getCurrentUser(@CurrentUser UserPrinciple userPrincipal) {
		System.out.println("aaaaaaaaaaaa");
		UserEntity userEntity =  userService.findUserByid(userPrincipal.getId());
		System.out.println("User entity: " + userEntity);
		if(userEntity == null) {
			throw new ResourceNotFoundException("User", "id", userPrincipal.getId());
		}
		 
		return userEntity;
			
	}
}
