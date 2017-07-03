package com.itopener.zuul.route.zk.spring.boot.autoconfigure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.zuul.filters.ZuulProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(ZuulZookeeperRouteProperties.class)
public class ZuulZookeeperRouteAutoConfiguration {

	@Autowired
	ZuulProperties zuulProperties;

	@Autowired
	ServerProperties server;

	@Bean(initMethod = "init")
	public CuratorFrameworkClient curatorFrameworkClient(){
		return new CuratorFrameworkClient();
	}
	
	@Bean(initMethod = "init")
	public ZookeeperTreeCacheListener zookeeperTreeCacheListener(){
		return new ZookeeperTreeCacheListener();
	}

	@Bean
	public ZuulZookeeperRouteLocator zuulZookeeperRouteLocator() {
		return new ZuulZookeeperRouteLocator(this.server.getServletPrefix(), this.zuulProperties);
	}
	
	@Bean
	public RefreshRouteService refreshRouteService(){
		return new RefreshRouteService();
	}
}
