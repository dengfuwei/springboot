package com.itopener.lock.zk.spring.boot.autoconfigure;

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

import com.itopener.lock.zk.spring.boot.autoconfigure.lock.DistributedLock;
import com.itopener.lock.zk.spring.boot.autoconfigure.lock.ZookeeperDistributedLock;

/**  
 * @author fuwei.deng
 * @Date 2017年6月9日 下午3:10:58
 * @version 1.0.0
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
