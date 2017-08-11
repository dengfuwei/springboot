package com.itopener.demo.springcloud.config.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("config")
public class SpringCloudConfigController {
	
	@RequestMapping("{id}")
	public ResultMap call(@PathVariable long id){
		return ResultMap.buildSuccess().put("id", id);
	}
}
