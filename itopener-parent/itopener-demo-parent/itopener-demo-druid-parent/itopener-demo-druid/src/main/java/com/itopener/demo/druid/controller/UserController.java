package com.itopener.demo.druid.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itopener.demo.druid.model.User;
import com.itopener.demo.druid.service.UserService;
import com.itopener.framework.ResultMap;

/**  
 * @author fuwei.deng
 * @Date 2017年6月9日 下午3:10:58
 * @version 1.0.0
 */
@RestController
@RequestMapping("user")
public class UserController {
	
	@Resource
	private UserService userService;
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResultMap get(@PathVariable long id){
		User user = userService.select(id);
		return ResultMap.buildSuccess().put("user", user);
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResultMap add(User user){
		userService.add(user);
		return ResultMap.buildSuccess().put("user", user);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResultMap update(User user){
		userService.update(user);
		return ResultMap.buildSuccess().put("user", user);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	public ResultMap delete(@PathVariable long id){
		User user = new User();
		user.setId(id);
		userService.delete(user);
		return ResultMap.buildSuccess().put("user", user);
	}
	
	@RequestMapping(value = "/transaction/{id}", method = RequestMethod.POST)
	public ResultMap transaction(@PathVariable long id){
		User user = new User();
		user.setId(id);
		userService.transaction(user);
		return ResultMap.buildSuccess().put("user", user);
	}
}
