package com.itopener.demo.hystrix.client.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.itopener.demo.hystrix.client.config.HystrixClientConstant;
import com.itopener.framework.ResultMap;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class HystrtixRibbonService {
	
	private final Logger logger = LoggerFactory.getLogger(HystrtixRibbonService.class);

	@Resource
	private RestTemplate restTemplateBalanced;
	
	@Resource
	private RestTemplate restTemplate;
	
	@HystrixCommand(fallbackMethod = "callFallback")
	public ResultMap call(long id){
		try {
			return restTemplateBalanced.getForObject("http://" + HystrixClientConstant.EUREKA_SERVER_HYSTRIX + "/user/vo/" + id, ResultMap.class);
		} catch (RestClientException e) {
			logger.error("balanced rest exception", e);
		} catch (Exception e){
			logger.error("balanced exception ", e);
		}
		return ResultMap.buildFailed("rest template balanced failed");
	}
	
	public ResultMap callFallback(long id){
		return ResultMap.buildFailed("hystrix ribbon fallback : " + id);
    }
}
