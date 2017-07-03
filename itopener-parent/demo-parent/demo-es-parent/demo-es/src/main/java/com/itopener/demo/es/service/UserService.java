package com.itopener.demo.es.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.itopener.demo.es.model.User;
import com.itopener.demo.es.repos.UserRepository;

@Service
public class UserService {

	@Resource
	private UserRepository userRepository;
	
	public User save(User user){
		return userRepository.save(user);
	}
	
	public User get(long id){
		return userRepository.findOne(id);
	}
	
	public void clear(){
		userRepository.deleteAll();
	}
}
