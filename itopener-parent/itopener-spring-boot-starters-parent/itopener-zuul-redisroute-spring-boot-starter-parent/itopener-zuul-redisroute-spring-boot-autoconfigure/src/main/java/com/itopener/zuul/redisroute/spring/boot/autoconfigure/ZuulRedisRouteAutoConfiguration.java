package com.itopener.zuul.redisroute.spring.boot.autoconfigure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@ConditionalOnBean(RedisTemplate.class)
@EnableConfigurationProperties(ZuulRedisRouteProperties.class)
public class ZuulRedisRouteAutoConfiguration {

	@Autowired
	ZuulProperties zuulProperties;

	@Autowired
	ServerProperties server;

	@Bean
	@ConditionalOnBean(RedisTemplate.class)
	public ZuulRedisRouteLocator zuulRedisRouteLocator() {
		return new ZuulRedisRouteLocator(this.server.getServletPrefix(), this.zuulProperties);
	}
	
	@Bean
	public RefreshRouteService refreshRouteService(){
		return new RefreshRouteService();
	}
	
	@Bean
	public AutoRefreshRedisRouteJob autoRefreshRedisRouteJob(){
		return new AutoRefreshRedisRouteJob();
	}
}
