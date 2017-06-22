package com.itopener.demo.feign.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itopener.demo.feign.client.api.IFeignService;
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
@RequestMapping("feign")
public class FeignController {
	
	@Autowired
	private IFeignService feignService;

	@RequestMapping("call/{id}")
	public ResultMap call(@PathVariable long id){
		return feignService.vo(id);
	}
}
