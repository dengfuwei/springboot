package com.itopener.demo.feign.client.api;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.itopener.demo.feign.client.config.FeignClientConstant;
import com.itopener.demo.feign.sdk.vo.UserVO;

@FeignClient(FeignClientConstant.EUREKA_SERVER_FEIGN)
public interface IFeignService {

	@RequestMapping("user/vo/{id}")
	public UserVO vo(@PathVariable("id") long id);
	
}
