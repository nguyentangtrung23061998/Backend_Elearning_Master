package com.eleaning.api;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
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

import com.eleaning.bean.ResponseBean;
import com.eleaning.bean.UserBean;
import com.eleaning.entity.UserEntity;
import com.eleaning.service.IUserService;
import com.eleaning.util.Util;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/users")
public class UserRestAPI {
	
	private static final Logger logger = LoggerFactory.getLogger(UserRestAPI.class);
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("")
	private ResponseEntity<ResponseBean> getUser(){
		ResponseBean responseBean = new ResponseBean();
		try {
			List<UserEntity> users = userService.getUsers();
			responseBean.setData(users);
			responseBean.setSuccess();
			return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	@PostMapping("/upload/{id}")
	private ResponseEntity<ResponseBean> uploadUser(@PathVariable long id, @RequestParam("file") MultipartFile file) throws IOException {
		UserEntity user = userService.findUserByid(id);
		ResponseBean responseBean = new ResponseBean();
		if(user != null) {
			boolean checkUpload = Util.upload(file);
			if(checkUpload) {
				user.setImage(file.getOriginalFilename());
				responseBean.setData(user);
				responseBean.setSuccess();
				return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.OK);
			}else {
				responseBean.setFailUpload();
				return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.OK);
			}
		}
		return null;
	}
	
	@PutMapping("/{id}")
	private ResponseEntity<ResponseBean> updateUser(@PathVariable long id, @RequestBody UserBean userBean){
		ResponseBean responseBean = new ResponseBean();
		try {
			System.out.println("id: " + id);
			UserEntity user = userService.findUserByid(id);	
			System.out.println("user: " + user.getFullname());
			user.setUsername(userBean.getUsername());
			user.setPassword(passwordEncoder.encode(userBean.getPassword()));;
			user.setEmail(userBean.getEmail());
			user.setFullname(userBean.getFullname());
			
			userService.save(user);
			responseBean.setData(user);
			responseBean.setSuccess();
			return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}
	
	@DeleteMapping("/{id}")
	private ResponseEntity<ResponseBean> deleteUser(@PathVariable long id){
		ResponseBean responseBean = new ResponseBean();
		userService.delete(id);
		responseBean.setData("{}");
		responseBean.setSuccess();
		return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.OK);
	}
}
