package com.itopener.demo.mongodb.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.itopener.demo.mongodb.model.User;
import com.itopener.demo.mongodb.repos.UserRepository;

@Service
public class UserService {

	@Resource
	private UserRepository userRepository;
	
	public User save(User user){
		return userRepository.save(user);
	}
}
