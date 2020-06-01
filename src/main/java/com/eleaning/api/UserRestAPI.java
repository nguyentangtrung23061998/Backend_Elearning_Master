package com.eleaning.api;

import java.io.IOException;
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

import com.eleaning.bean.MapBean;
import com.eleaning.bean.ResponseBean;
import com.eleaning.bean.RoleNameBean;
import com.eleaning.bean.UserBean;
import com.eleaning.conveter.UserConverter;
import com.eleaning.entity.RoleEntity;
import com.eleaning.entity.UserEntity;
import com.eleaning.service.IRoleService;
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
	private IRoleService roleService;

	@Autowired
	private UserConverter userConverter;

	@Autowired
	private PasswordEncoder passwordEncoder;

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
	private ResponseEntity<ResponseBean> getUser() {
		ResponseBean responseBean = new ResponseBean();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		try {
			List list = (List) authentication.getAuthorities();
			boolean check = checkRole(list);
			if(!check) {
				responseBean.setRoleFail();
				return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.BAD_REQUEST);
			}
			
			List<UserEntity> users = userService.getUsers();
			
			MapBean map = new MapBean();
			List listUsers = new ArrayList();
			for (int i = 0; i < users.size(); i++) {
				UserEntity user = new UserEntity();
				map = new MapBean();
				user = users.get(i);
				map.put("role", user.getRole());
				listUsers.add(map.getAll());
			}
			//xử lý qua bean -> để đưa role 
			responseBean.setData(listUsers);
			responseBean.setSuccess();
			return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@GetMapping("/{id}")
	private ResponseEntity<ResponseBean> getUserById(@PathVariable Long id) {
		ResponseBean responseBean = new ResponseBean();
		try {
			UserEntity user = userService.findUserByid(id);
			if(user != null) {
				responseBean.setData(user);
				responseBean.setSuccess();
				return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
			}else {
				return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
	}
	
	@PostMapping("")
	private ResponseEntity<ResponseBean> addUser() {
		ResponseBean responseBean = new ResponseBean();
		try {
			Long id = 1588172402901L;
			UserEntity user = userService.findUserByid(id);
			if(user != null) {
				responseBean.setData(user);
				responseBean.setSuccess();
				return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
			}else {
				return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
	}
	
	@PostMapping("/uploads/{id}")
	private ResponseEntity<ResponseBean> uploadUsers(@PathVariable long id, @RequestParam("file") MultipartFile file)
			throws IOException {
		UserEntity user = userService.findUserByid(id);
		ResponseBean responseBean = new ResponseBean();
		try {
			if (user != null) {
				boolean checkUpload = Util.upload(file);
				if (checkUpload) {
					user.setImage(file.getOriginalFilename());
					userService.save(user);
					responseBean.setData(user);
					responseBean.setSuccess();
					return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
				} else {
					responseBean.setFailUpload();
					return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.BAD_REQUEST);
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		
		return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	private ResponseEntity<ResponseBean> updateUser(@PathVariable long id, @RequestBody UserBean userBean) {
		ResponseBean responseBean = new ResponseBean();
		try {
			Set<RoleEntity> roles = new HashSet<RoleEntity>();
			UserEntity user = userService.findUserByid(id);
			RoleEntity role = roleService.findByRolename(userBean.getRole());
			if (role == null) {
				responseBean.setRoleUserNotFound();
				return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.BAD_REQUEST);
			}

			user.setUsername(userBean.getUsername());
			user.setPassword(passwordEncoder.encode(userBean.getPassword()));
			user.setEmail(userBean.getEmail());
			user.setFullname(userBean.getFullname());
			user.setImage(userBean.getImage());

			roles.add(role);
			user.setRole(roles);
			UserEntity userEntity = userService.save(user);
			UserBean userOutput = userConverter.convertBean(userEntity);
			userOutput.setImage(user.getImage());
			userOutput.setRole(userBean.getRole());
				
			responseBean.setData(userOutput);
			responseBean.setSuccess();
			return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@DeleteMapping("/{id}")
	private ResponseEntity<ResponseBean> deleteUser(@PathVariable long id) {
		ResponseBean responseBean = new ResponseBean();
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		List list = (List) authentication.getAuthorities();
		boolean check = checkRole(list);
		if(!check) {
			responseBean.setRoleFail();
			return new ResponseEntity<ResponseBean>(responseBean,HttpStatus.BAD_REQUEST);
		}
		
		userService.delete(id);
		responseBean.setData("{}");
		responseBean.setSuccess();
		return new ResponseEntity<ResponseBean>(responseBean, HttpStatus.OK);
	}
}
