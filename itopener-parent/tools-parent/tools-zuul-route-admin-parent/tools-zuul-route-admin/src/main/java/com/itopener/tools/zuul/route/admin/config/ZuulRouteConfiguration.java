package com.itopener.tools.zuul.route.admin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.itopener.framework.base.BaseDao;
import com.zuul.route.spring.boot.common.ZuulRouteEntity;

@Configuration
public class ZuulRouteConfiguration {

	@Bean
	public BaseDao baseDao(){
		return new BaseDao();
	}
	
	@Bean(initMethod = "init")
	public CuratorFrameworkClient curatorFrameworkClient(){
		return new CuratorFrameworkClient();
	}
	
	@Bean("redisTemplate")
	public RedisTemplate<String, ZuulRouteEntity> redisTemplate(RedisConnectionFactory redisConnectionFactory){
		RedisTemplate<String, ZuulRouteEntity> redisTmplate = new RedisTemplate<String, ZuulRouteEntity>();
		redisTmplate.setConnectionFactory(redisConnectionFactory);
		redisTmplate.setKeySerializer(new StringRedisSerializer());
		redisTmplate.setHashKeySerializer(new StringRedisSerializer());
		return redisTmplate;
	}
}
