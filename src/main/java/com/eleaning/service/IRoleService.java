package com.eleaning.service;

import com.eleaning.entity.RoleEntity;

public interface IRoleService {
	public RoleEntity findByUsername(String rolename);
}
