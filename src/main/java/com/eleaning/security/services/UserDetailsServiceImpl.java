package com.eleaning.security.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.eleaning.entity.UserEntity;
import com.eleaning.repository.IUserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired 
	private IUserRepository userRepository;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 UserEntity user = userRepository.findByUsername(username)
             	.orElseThrow(() -> 
                     new UsernameNotFoundException("User Not Found with -> username or email : " + username)
         );
		 return UserPrinciple.build(user);
	}	

}
