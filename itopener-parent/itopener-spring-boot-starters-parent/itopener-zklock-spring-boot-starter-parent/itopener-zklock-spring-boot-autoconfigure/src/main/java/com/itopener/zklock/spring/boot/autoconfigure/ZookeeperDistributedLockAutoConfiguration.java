/**  
 * Project Name:msxf-lock-spring-boot-autoconfigure 
 * File Name:ZookeeperDistributedLockAutoConfiguration.java  
 * Package Name:com.msxf.lock.spring.boot.autoconfigure  
 * Date:2017年5月10日上午10:04:57 
 * Copyright (c)2015, 马上消费金融股份有限公司  All Rights Reserved.  
 */ 
package com.itopener.zklock.spring.boot.autoconfigure;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.RetryNTimes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.itopener.zklock.spring.boot.autoconfigure.lock.DistributedLock;
import com.itopener.zklock.spring.boot.autoconfigure.lock.ZookeeperDistributedLock;

/**
 * Created by fuwei.deng on 2017年5月10日.
 */
@Configuration
@EnableConfigurationProperties(ZookeeperDistributedLockProperties.class)
public class ZookeeperDistributedLockAutoConfiguration {
	
	private final Logger logger = LoggerFactory.getLogger(ZookeeperDistributedLockAutoConfiguration.class);
	
	@Autowired
	private ZookeeperDistributedLockProperties zookeeperDistributedLockProperties;

	@Bean
	public CuratorFramework curatorFramework(){
		CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient(zookeeperDistributedLockProperties.getConnect(), new RetryNTimes(10, 5000));
		curatorFramework.start();
        logger.info("zookeeper distributed lock started, zk state : " + curatorFramework.getState().name());
        return curatorFramework;
	}
	
	@Bean
	@ConditionalOnProperty("spring.lock.zk.connect")
	@ConditionalOnBean(CuratorFramework.class)
	public DistributedLock zookeeperDistributedLock(CuratorFramework curatorFramework){
		return new ZookeeperDistributedLock(curatorFramework, zookeeperDistributedLockProperties.getPath());
	}
}
