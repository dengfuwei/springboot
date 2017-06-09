/**  
 * Project Name:msxf-lock-spring-boot-autoconfigure 
 * File Name:DistributedProperties.java  
 * Package Name:com.msxf.lock.spring.boot.autoconfigure  
 * Date:2017年5月10日上午9:48:00 
 * Copyright (c)2015, 马上消费金融股份有限公司  All Rights Reserved.  
 */ 
package com.itopener.zklock.spring.boot.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by fuwei.deng on 2017年5月10日.
 */
@ConfigurationProperties(prefix="spring.lock.zk")
public class ZookeeperDistributedLockProperties {

	private String connect = "localhost:2181";
	
	private String path = "/zklock";

	public String getConnect() {
		return connect;
	}

	public void setConnect(String connect) {
		this.connect = connect;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
}
