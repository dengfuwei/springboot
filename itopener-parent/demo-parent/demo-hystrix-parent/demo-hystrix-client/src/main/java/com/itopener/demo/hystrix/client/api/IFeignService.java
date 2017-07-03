package com.itopener.demo.hystrix.client.api;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itopener.demo.hystrix.client.config.HystrixClientConstant;
import com.itopener.demo.hystrix.client.service.FeignServiceFallback;
import com.itopener.framework.ResultMap;

@FeignClient(value = HystrixClientConstant.EUREKA_SERVER_HYSTRIX, fallback = FeignServiceFallback.class)
public interface IFeignService {

	@RequestMapping("user/vo/{id}")
	public ResultMap vo(@PathVariable("id") long id);
	
}
