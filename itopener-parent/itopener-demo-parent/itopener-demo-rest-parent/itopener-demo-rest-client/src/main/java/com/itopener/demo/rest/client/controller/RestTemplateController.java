package com.itopener.demo.rest.client.controller;

import javax.annotation.Resource;

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
@RequestMapping("rest")
public class RestTemplateController {
	
	@Resource
	private RestTemplate restTemplate;

	@RequestMapping("call/{id}")
	public ResultMap call(@PathVariable long id){
		return restTemplate.getForObject("http://localhost:8081/user/vo/1", ResultMap.class);
	}
}
