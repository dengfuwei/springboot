package com.itopener.demo.zuul.client.redis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.itopener.zuul.route.spring.boot.common.ZuulRouteEntity;

/**
 * @author fuwei.deng
 * @date 2017年6月14日 下午4:55:27
 * @version 1.0.0
 */
@Configuration
public class RibbonClientConfiguration {

	@Bean("redisTemplate")
	public RedisTemplate<String, ZuulRouteEntity> redisTemplate(RedisConnectionFactory redisConnectionFactory){
		RedisTemplate<String, ZuulRouteEntity> redisTmplate = new RedisTemplate<String, ZuulRouteEntity>();
		redisTmplate.setConnectionFactory(redisConnectionFactory);
		redisTmplate.setKeySerializer(new StringRedisSerializer());
		redisTmplate.setHashKeySerializer(new StringRedisSerializer());
		return redisTmplate;
	}
}
