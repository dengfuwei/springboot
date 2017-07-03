package com.itopener.demo.jsonp.fastjson.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.alibaba.fastjson.support.spring.FastJsonpResponseBodyAdvice;

@Configuration
public class FastJsonpConfiguration {

	//配置fastjson的ResponseBodyAdvice
	@Bean
	public FastJsonpResponseBodyAdvice fastJsonpResponseBodyAdvice() {
	    return new FastJsonpResponseBodyAdvice("callback");
	}
}
