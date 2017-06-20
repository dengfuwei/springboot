package com.itopener.demo.springcloud.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.itopener.demo.springcloud.web.vo.UserVO;
import com.itopener.framework.ResultMap;

@RestController
@RequestMapping("springcloud")
public class SpringCloudController {

	private final Logger logger = LoggerFactory.getLogger(SpringCloudController.class);
	
	@RequestMapping("index")
	public ResultMap index(UserVO user){
		logger.info("SpringCloudController.index(UserVO user) params : " + JSON.toJSONString(user));
		return ResultMap.buildSuccess().put("user", user);
	}
}
