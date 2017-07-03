package com.itopener.demo.mongodb.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itopener.demo.mongodb.enums.RoleStateEnum;
import com.itopener.demo.mongodb.enums.UserStateEnum;
import com.itopener.demo.mongodb.model.Role;
import com.itopener.demo.mongodb.model.User;
import com.itopener.demo.mongodb.service.UserService;
import com.itopener.framework.ResultMap;
import com.itopener.utils.TimestampUtil;

@RestController
@RequestMapping("user")
public class UserController {

	@Resource
	private UserService userService;
	
	@RequestMapping("save")
	public ResultMap save(User user){
		Timestamp now = TimestampUtil.current();
		user.setId(new Random().nextLong());
		user.setCreateTime(now);
		user.setName("fuwei.deng");
		user.setState(UserStateEnum.CODE_NORMAL);
		user.setUpdateTime(now);
		
		user.setRoleList(new ArrayList<Role>());
		for(int i=0; i<10; i++){
			Role role = new Role();
			role.setCreateTime(now);
			role.setId(new Random().nextLong());
			role.setName("fuwei.deng." + i);
			role.setState(RoleStateEnum.CODE_NORMAL);
			role.setUpdateTime(now);
			user.getRoleList().add(role);
		}
		
		user = userService.save(user);
		return ResultMap.buildSuccess().put("user", user);
	}
}
