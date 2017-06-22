package com.itopener.demo.hystrix.client.service;

import org.springframework.stereotype.Service;

import com.itopener.demo.hystrix.client.api.IFeignService;
import com.itopener.framework.ResultMap;

@Service
public class FeignServiceFallback implements IFeignService {

	@Override
	public ResultMap vo(long id) {
		return ResultMap.buildFailed("hystrix feign fallback : " + id);
	}

}
