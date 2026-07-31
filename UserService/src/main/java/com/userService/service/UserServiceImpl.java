package com.userService.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.userService.entity.User;
import com.userService.exception.ResourceNotFound;
import com.userService.repository.UserRepository;
@Service
public class UserServiceImpl  implements UserService{
@Autowired
private UserRepository userrepository;
	@Override
	public User saveUser(User user) {
//		String randomuserid=UUID.randomUUID().toString();
//		user.setId(randomuserid);
		return userrepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		return userrepository.findAll();
	}

	@Override
	public User getUserById(Long id) {
		return userrepository.findById(id).orElseThrow( ()->new ResourceNotFound());
		
	}



}
