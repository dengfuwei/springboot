package com.itopener.demo.ribbon.client.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.itopener.demo.ribbon.client.config.RibbonClientConstant;
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
@RequestMapping("ribbon")
public class RibbonController {
	
	private final Logger logger = LoggerFactory.getLogger(RibbonController.class);
	
	@Resource
	private RestTemplate restTemplateBalanced;
	
	@Resource
	private RestTemplate restTemplate;

	@RequestMapping("call/balanced/{id}")
	public ResultMap callBalanced(@PathVariable long id){
		try {
			return restTemplateBalanced.getForObject("http://" + RibbonClientConstant.EUREKA_SERVER_RIBBON + "/user/vo/1", ResultMap.class);
		} catch (RestClientException e) {
			logger.error("balanced rest exception", e);
		}
		
		return ResultMap.buildFailed("balanced rest failed");
	}
	
	@RequestMapping("call/{id}")
	public ResultMap call(@PathVariable long id){
		try {
			//会报UnknowHostException
			return restTemplate.getForObject("http://" + RibbonClientConstant.EUREKA_SERVER_RIBBON + "/user/vo/1", ResultMap.class);
		} catch (RestClientException e) {
			logger.error("rest exception", e);
		}
		return ResultMap.buildFailed("rest failed");
	}
}
