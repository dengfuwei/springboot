/**  
 * Project Name:msxf-tools-redis 
 * File Name:ToolsRedisConfigurer.java  
 * Package Name:com.msxf.tools.redis.config  
 * Date:2017年5月12日下午2:53:04 
 * Copyright (c)2015, 马上消费金融股份有限公司  All Rights Reserved.  
 */ 
package com.itopener.tools.redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Created by fuwei.deng on 2017年5月12日.
 */
@Configuration
public class ToolsRedisConfigurer {

	@Bean("redisTemplate")
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory){
		RedisTemplate<String, Object> redisTmplate = new RedisTemplate<String, Object>();
		redisTmplate.setConnectionFactory(redisConnectionFactory);
		redisTmplate.setKeySerializer(new StringRedisSerializer());
		redisTmplate.setHashKeySerializer(new StringRedisSerializer());
		return redisTmplate;
	}
	
}
