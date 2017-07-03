package com.itopener.demo.redis.controller;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itopener.demo.redis.vo.UserVO;
import com.itopener.framework.ResultMap;
import com.itopener.utils.TimestampUtil;

@RestController
@RequestMapping("redis")
public class RedisController {
	
	@Resource
	private RedisTemplate<String, UserVO> redisTemplate;

	@RequestMapping("user/{id}")
	public ResultMap save(@PathVariable long id){
		UserVO user = new UserVO();
		user.setCreateTime(TimestampUtil.current());
		user.setId(id);
		user.setName("name" + id);
		user.setState((byte) 1);
		redisTemplate.opsForValue().set("user", user, 60, TimeUnit.SECONDS);
		return ResultMap.buildSuccess();
	}
	
	@RequestMapping("{key}")
	public ResultMap get(@PathVariable String key){
		UserVO user = redisTemplate.opsForValue().get(key);
		return ResultMap.buildSuccess().put("user", user);
	}
}
