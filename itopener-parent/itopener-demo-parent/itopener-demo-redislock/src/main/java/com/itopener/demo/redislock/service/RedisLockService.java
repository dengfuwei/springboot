package com.itopener.demo.redislock.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.itopener.redislock.spring.boot.autoconfigure.annotations.RedisLock;

@Service
public class RedisLockService {

	private final Logger logger = LoggerFactory.getLogger(RedisLockService.class);
	
	@RedisLock
	public void update(String key){
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			logger.error("exp", e);
		}
	}
	
}
