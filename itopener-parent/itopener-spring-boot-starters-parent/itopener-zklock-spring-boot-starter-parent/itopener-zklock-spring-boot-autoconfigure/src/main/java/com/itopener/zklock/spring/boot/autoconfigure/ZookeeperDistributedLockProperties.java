package com.itopener.zklock.spring.boot.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**  
 * @author fuwei.deng
 * @Date 2017年6月9日 下午3:10:58
 * @version 1.0.0
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
