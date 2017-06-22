package com.itopener.demo.rest.server.controller;

import java.util.Random;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itopener.demo.rest.sdk.vo.UserVO;
import com.itopener.framework.ResultMap;
import com.itopener.utils.TimestampUtil;

@RestController
@RequestMapping("user")
public class UserController {

	@RequestMapping("result")
	public ResultMap result() {
		UserVO user = new UserVO();
		user.setId(new Random().nextLong());
		user.setName("name" + user.getId());
		user.setCreateTime(TimestampUtil.current());
		return ResultMap.buildSuccess().put("user", user);
	}
	
	@RequestMapping("vo/{id}")
	public ResultMap vo(@PathVariable long id) {
		UserVO user = new UserVO();
		user.setId(id);
		user.setName("name" + user.getId());
		user.setCreateTime(TimestampUtil.current());
		return ResultMap.buildSuccess().put("user", user);
	}
}
