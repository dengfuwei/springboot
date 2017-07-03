package com.tools.zuul.route.admin.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.jdbc.core.JdbcTemplate;

import com.zuul.route.spring.boot.common.ZuulRouteEntity;

@Configuration
public class ZuulRouteConfiguration {

	@Autowired
	DataSource dataSource;
	
	@Bean
	public JdbcTemplate jdbcTemplate(){
		return new JdbcTemplate(dataSource);
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
