package com.itopener.demo.zipkin3.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.itopener.demo.zipkin.sdk.UserVO;
import com.itopener.framework.ResultMap;

/**  
 * @ClassName:RestController <br/> 
 * @Description <br/>
 * @date 2017年4月11日下午6:04:40 <br/>
 * @author fuwei.deng
 * @version 
 * @since JDK 1.6  
 * @see 
 */
@RestController
@RequestMapping("user")
public class RestTemplateController {
	
	private final Logger logger = LoggerFactory.getLogger(RestTemplateController.class);
	
	@Resource
	private RestTemplate restTemplate;

	@RequestMapping("vo/{id}")
	public ResultMap call(@PathVariable long id){
		logger.info("demo-zipkin3");
		UserVO user = new UserVO();
		user.setName("zipkin3");
		return ResultMap.buildSuccess().put("user", user);
	}
}
