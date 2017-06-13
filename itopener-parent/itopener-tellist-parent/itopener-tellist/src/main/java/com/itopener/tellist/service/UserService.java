package com.itopener.tellist.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.itopener.tellist.conditions.UserCondition;
import com.itopener.tellist.dao.UserDao;
import com.itopener.tellist.model.User;

@Service
public class UserService {

	@Resource
	private UserDao userDao;
	
	public User login(UserCondition condition){
		return userDao.selectOne(condition);
	}
}
