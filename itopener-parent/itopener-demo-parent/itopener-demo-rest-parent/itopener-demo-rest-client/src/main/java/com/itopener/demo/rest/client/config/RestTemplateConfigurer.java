package com.itopener.demo.rest.client.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter4;

/**
 * @author fuwei.deng
 * @date 2017年6月14日 下午4:55:27
 * @version 1.0.0
 */
@Configuration
public class RestTemplateConfigurer {

	// 使用RestTemplateBuilder来实例化RestTemplate对象，spring默认已经注入了RestTemplateBuilder实例
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder restTtemplateBuilder) {
		restTtemplateBuilder.messageConverters(new FastJsonHttpMessageConverter4());
		return restTtemplateBuilder.build();
	}
}
