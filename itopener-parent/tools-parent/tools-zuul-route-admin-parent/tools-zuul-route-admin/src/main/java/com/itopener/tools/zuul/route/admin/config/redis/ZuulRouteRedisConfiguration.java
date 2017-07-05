package com.itopener.tools.zuul.route.admin.config.redis;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.itopener.tools.zuul.route.admin.service.impl.ZuulRouteRedisService;
import com.itopener.zuul.route.spring.boot.common.ZuulRouteEntity;

@Configuration
@ConditionalOnProperty("spring.redis.cluster.nodes")
@EnableConfigurationProperties(ZuulRouteRedisProperties.class)
public class ZuulRouteRedisConfiguration {
	
	@Bean("redisTemplate")
	public RedisTemplate<String, ZuulRouteEntity> redisTemplate(RedisConnectionFactory redisConnectionFactory){
		RedisTemplate<String, ZuulRouteEntity> redisTmplate = new RedisTemplate<String, ZuulRouteEntity>();
		redisTmplate.setConnectionFactory(redisConnectionFactory);
		redisTmplate.setKeySerializer(new StringRedisSerializer());
		redisTmplate.setHashKeySerializer(new StringRedisSerializer());
		return redisTmplate;
	}
	
	@Bean
	public ZuulRouteRedisService zuulRouteRedisService(){
		return new ZuulRouteRedisService();
	}
}
