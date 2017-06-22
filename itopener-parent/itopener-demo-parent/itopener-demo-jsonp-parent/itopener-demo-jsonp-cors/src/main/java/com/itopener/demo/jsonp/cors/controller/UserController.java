package com.itopener.demo.jsonp.cors.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itopener.demo.jsonp.cors.vo.UserVO;
import com.itopener.framework.ResultMap;
import com.itopener.utils.TimestampUtil;

@RestController
@RequestMapping("jsonp")
public class UserController {
	
	@RequestMapping("user/{id}")
	public ResultMap get(@PathVariable long id){
		UserVO user = new UserVO();
		user.setCreateTime(TimestampUtil.current());
		user.setId(id);
		user.setName("name" + id);
		user.setState((byte) 1);
		return ResultMap.buildSuccess().put("user", user);
	}
}
