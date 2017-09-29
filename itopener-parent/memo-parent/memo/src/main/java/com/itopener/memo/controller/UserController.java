package com.itopener.memo.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.itopener.framework.ResultMap;

@RestController
@RequestMapping("user")
public class UserController {
	
	private final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Resource
	private RestTemplate restTemplate;
	
	@Value("${wechat.app.id}")
	private String appId;
	
	@Value("${wechat.app.secret}")
	private String appSecret;

	@GetMapping("login")
	public ResultMap onLogin(String code){
		logger.info(code);
		String resp = restTemplate.getForObject("https://api.weixin.qq.com/sns/jscode2session?appid={appId}&secret={appSecret}&js_code={code}&grant_type=authorization_code", String.class, appId, appSecret, code);
		logger.info(resp);
		return ResultMap.buildSuccess();
	}
}
