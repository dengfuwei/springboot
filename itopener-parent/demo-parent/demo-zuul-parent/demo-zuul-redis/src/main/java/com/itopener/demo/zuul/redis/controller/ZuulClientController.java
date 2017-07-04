package com.itopener.demo.zuul.redis.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itopener.demo.zuul.sdk.vo.UserVO;
import com.itopener.framework.ResultMap;
import com.itopener.utils.TimestampUtil;

@RestController
@RequestMapping("zuulclient")
public class ZuulClientController {

	@RequestMapping("call/{id}")
	public ResultMap callBalanced(@PathVariable long id){
		UserVO user = new UserVO();
		user.setCreateTime(TimestampUtil.current());
		user.setId(id);
		user.setName("name" + id);
		return ResultMap.buildSuccess().put("user", user);
	}
}
