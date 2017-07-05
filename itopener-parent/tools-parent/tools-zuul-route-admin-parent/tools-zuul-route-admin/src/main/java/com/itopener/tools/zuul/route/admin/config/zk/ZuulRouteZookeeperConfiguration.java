package com.itopener.tools.zuul.route.admin.config.zk;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.itopener.tools.zuul.route.admin.service.impl.ZuulRouteZookeeperService;

@Configuration
@ConditionalOnProperty("spring.zuul.route.admin.zk.serverLists")
@EnableConfigurationProperties(ZuulRouteZookeeperProperties.class)
public class ZuulRouteZookeeperConfiguration {

	@Bean(initMethod = "init")
	public CuratorFrameworkClient curatorFrameworkClient(){
		return new CuratorFrameworkClient();
	}
	
	@Bean
	public ZuulRouteZookeeperService zuulRouteZookeeperService(){
		return new ZuulRouteZookeeperService();
	}
}
