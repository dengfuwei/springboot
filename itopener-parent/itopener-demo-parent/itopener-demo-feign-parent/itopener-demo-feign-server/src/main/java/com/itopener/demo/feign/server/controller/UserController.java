package com.itopener.demo.feign.server.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itopener.demo.feign.sdk.vo.UserVO;
import com.itopener.framework.ResultMap;
import com.itopener.utils.TimestampUtil;

@RestController
@RequestMapping("user")
public class UserController {
	
	@Value("${app.index}")
	private String index;

	@RequestMapping("result")
	public ResultMap result() {
		UserVO user = new UserVO();
		user.setId(new Random().nextLong());
		user.setName("name" + index);
		user.setCreateTime(TimestampUtil.current());
		return ResultMap.buildSuccess().put("user", user);
	}
	
	@RequestMapping("vo/{id}")
	public UserVO vo(@PathVariable long id) {
		UserVO user = new UserVO();
		user.setId(id);
		user.setName("name" + index);
		user.setCreateTime(TimestampUtil.current());
		return user;
	}
}
