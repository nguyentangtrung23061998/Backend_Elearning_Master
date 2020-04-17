package com.eleaning.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eleaning.entity.RoleEntity;
import com.eleaning.entity.UserEntity;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {
	Boolean existsByUsername(String username);
	Boolean existsByEmail(String email);
	@Query("SELECT m FROM UserEntity m WHERE username = :username ")
	Optional<UserEntity> findByUsername(@Param("username") String username);
}
