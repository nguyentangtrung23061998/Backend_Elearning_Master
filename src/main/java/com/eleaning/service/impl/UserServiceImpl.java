package com.eleaning.service.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

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
			
		}else {
			entity.setModifieddate(new Timestamp(System.currentTimeMillis()));
		}
		userRepository.save(entity);
		return entity;
	}

	@Override
	public UserEntity findUser(String username) {
		try {
			Optional<UserEntity> data = userRepository.findByUsername(username);
			if (data.isPresent()) {
				return data.get();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Boolean existsByUsername(String username) {
		return userRepository.existsByUsername(username);
	}

	@Override
	public Boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}

	@Override
	public UserEntity findUserByid(long id) {
		try {
			Optional<UserEntity> data = userRepository.findById(id);
			if (data.isPresent()) {
				return data.get();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<UserEntity> getUsers() {
		return userRepository.findAll();
	}

	@Override
	public void delete(Long id) {
		userRepository.deleteById(id);
	}
	
	
}
