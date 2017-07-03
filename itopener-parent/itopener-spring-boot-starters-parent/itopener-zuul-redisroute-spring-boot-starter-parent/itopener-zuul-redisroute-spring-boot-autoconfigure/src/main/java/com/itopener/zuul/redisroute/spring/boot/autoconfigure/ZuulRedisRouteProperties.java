package com.itopener.zuul.redisroute.spring.boot.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="spring.zuul.route.redis")
public class ZuulRedisRouteProperties {

	private String namespace = "zuul_route_config";

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}
	
}
