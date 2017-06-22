package com.itopener.demo.zuul.server2.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itopener.demo.zuul.sdk.vo.UserVO;
import com.itopener.framework.ResultMap;
import com.itopener.utils.TimestampUtil;

@RestController
@RequestMapping("user")
public class UserController {
	
	private final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@RequestMapping("vo/{id}")
	public ResultMap vo(@PathVariable long id) {
		logger.info("zuul server 2 : " + id);
		UserVO user = new UserVO();
		user.setId(id);
		user.setName("name" + id);
		user.setCreateTime(TimestampUtil.current());
		return ResultMap.buildSuccess().put("user", user);
	}
}
