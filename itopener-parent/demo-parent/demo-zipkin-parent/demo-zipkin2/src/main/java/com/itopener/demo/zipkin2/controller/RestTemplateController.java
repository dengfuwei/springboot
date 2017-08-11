package com.itopener.demo.zipkin2.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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
		logger.info("demo-zipkin2");
		ResultMap user3 = restTemplate.getForObject("http://localhost:8083/user/vo/1", ResultMap.class);
		ResultMap user4 = restTemplate.getForObject("http://localhost:8084/user/vo/1", ResultMap.class);
		return ResultMap.buildSuccess().put("user3", user3).put("user4", user4);
	}
}
