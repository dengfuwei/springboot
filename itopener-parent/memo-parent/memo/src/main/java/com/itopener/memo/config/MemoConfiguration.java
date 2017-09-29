package com.itopener.memo.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.itopener.framework.base.BaseDao;

@Configuration
public class MemoConfiguration {

	@Bean
	public BaseDao baseDao(){
		return new BaseDao();
	}
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder restTemplateBuilder) {
		return restTemplateBuilder.build();
	}
}
