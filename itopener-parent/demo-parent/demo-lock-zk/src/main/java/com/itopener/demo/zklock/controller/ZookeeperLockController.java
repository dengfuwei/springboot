package com.itopener.demo.zklock.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itopener.framework.ResultMap;
import com.itopener.lock.zk.spring.boot.autoconfigure.lock.DistributedLock;

@RestController
@RequestMapping("zklock")
public class ZookeeperLockController {
	
	private final Logger logger = LoggerFactory.getLogger(ZookeeperLockController.class);

	@Resource
	private DistributedLock zookeeperDistributedLock;
	
	@RequestMapping("lock")
	public ResultMap lock(){
		for(int i=0; i<10; i++){
			new RedisLockThread().start();
		}
		return ResultMap.buildSuccess();
	}
	
	class RedisLockThread extends Thread {

		@Override
		public void run() {
			String key = "lockKey";
			boolean result = zookeeperDistributedLock.lock(key, 0);
			logger.info(result ? "get lock success : " + key : "get lock failed : " + key);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				logger.error("exp", e);
			} finally {
				zookeeperDistributedLock.releaseLock(key);
				logger.info("release lock : " + key);
			}
		}
	}
	
}
