package com.itopener.demo.mongodb.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itopener.demo.mongodb.model.User;
import com.itopener.demo.mongodb.service.UserService;
import com.itopener.framework.ResultMap;

@RestController
@RequestMapping("user")
public class UserController {

	@Resource
	private UserService userService;
	
	@RequestMapping("save")
	public ResultMap save(User user){
		user = userService.save(user);
		return ResultMap.buildSuccess().put("user", user);
	}
}
