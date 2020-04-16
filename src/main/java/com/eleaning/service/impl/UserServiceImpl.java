package com.eleaning.service.impl;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eleaning.entity.UserEntity;
import com.eleaning.repository.IUserRepository;
import com.eleaning.service.IUserService;

@Service
public class UserServiceImpl implements IUserService{
	@Autowired
	private IUserRepository userRepository;

	@Override
	public UserEntity save(UserEntity entity) {
		if(entity.getId() == 0) {
			entity.setCreateddate(new Timestamp(System.currentTimeMillis()));
			
		}
		userRepository.save(entity);
		return entity;
	}
	
	
}
