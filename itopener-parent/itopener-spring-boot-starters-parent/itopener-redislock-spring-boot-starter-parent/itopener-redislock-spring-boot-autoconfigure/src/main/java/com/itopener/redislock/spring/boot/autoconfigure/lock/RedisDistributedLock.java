package com.itopener.redislock.spring.boot.autoconfigure.lock;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author fuwei.deng
 * @date 2017年6月14日 下午3:11:14
 * @version 1.0.0
 */
public class RedisDistributedLock extends AbstractDistributedLock {
	
	private final Logger logger = LoggerFactory.getLogger(RedisDistributedLock.class);
	
	private RedisTemplate<Object, Object> redisTemplate;

	public RedisDistributedLock(RedisTemplate<Object, Object> redisTemplate) {
		super();
		this.redisTemplate = redisTemplate;
	}

	@Override
	public synchronized boolean lock(String key, long expire, int retryTimes, long sleepMillis) {
		Boolean result = redisTemplate.opsForValue().setIfAbsent(key, 1L);
		while((result == null || !result.booleanValue()) && retryTimes-- > 0){
			try {
				logger.info("lock failed, retrying..." + retryTimes);
				Thread.sleep(sleepMillis);
			} catch (InterruptedException e) {
				return false;
			}
			result = redisTemplate.opsForValue().setIfAbsent(key, 1L);
		}
		if(result != null && result.booleanValue()){
			result = redisTemplate.boundValueOps(key).expire(expire, TimeUnit.MILLISECONDS);
			if(result != null && result.booleanValue()){
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean releaseLock(String key) {
		try {
			redisTemplate.delete(key);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

}
