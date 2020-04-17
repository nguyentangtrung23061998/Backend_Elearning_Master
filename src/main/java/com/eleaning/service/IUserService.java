package com.eleaning.service;

import com.eleaning.entity.UserEntity;

public interface IUserService {
	public UserEntity findUser(String username);
	public UserEntity save(UserEntity entity);
	Boolean existsByUsername(String username);
	Boolean existsByEmail(String email);
}
