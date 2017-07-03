package com.itopener.demo.redislock.controller;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itopener.demo.redislock.service.RedisLockService;
import com.itopener.framework.ResultMap;
import com.itopener.lock.redis.spring.boot.autoconfigure.lock.DistributedLock;

@RestController
@RequestMapping("redislock")
public class RedisLockController {
	
	private final Logger logger = LoggerFactory.getLogger(RedisLockController.class);

	@Resource
	private RedisLockService redisLockService;
	
	@Resource
	private DistributedLock redisDistributedLock;
	
	@RequestMapping("aspect")
	public ResultMap lockAspect(){
		for(int i=0; i<10; i++){
			new RedisLockAspectThread().start();
		}
		return ResultMap.buildSuccess();
	}
	
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
			boolean result = redisDistributedLock.lock(key);
			logger.info(result ? "get lock success : " + key : "get lock failed : " + key);
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				logger.error("exp", e);
			} finally {
				redisDistributedLock.releaseLock(key);
				logger.info("release lock : " + key);
			}
		}
	}
	
	class RedisLockAspectThread extends Thread {
		
		@Override
		public void run() {
			String key = "lockKey2";
			redisLockService.update(key);
		}
	}
}
