package com.itopener.zuul.dbroute.spring.boot.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix="spring.zuul.route.db")
public class ZuulDBRouteProperties {

	private String tableName = "zuul_route_config";

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
}
