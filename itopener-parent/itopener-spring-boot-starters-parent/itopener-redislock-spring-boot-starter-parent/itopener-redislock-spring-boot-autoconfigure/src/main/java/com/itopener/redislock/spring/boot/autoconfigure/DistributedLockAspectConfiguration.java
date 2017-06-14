package com.itopener.redislock.spring.boot.autoconfigure;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import com.itopener.redislock.spring.boot.autoconfigure.annotations.RedisLock;
import com.itopener.redislock.spring.boot.autoconfigure.annotations.RedisLock.LockFailAction;
import com.itopener.redislock.spring.boot.autoconfigure.lock.DistributedLock;

/**
 * @author fuwei.deng
 * @date 2017年6月14日 下午3:11:22
 * @version 1.0.0
 */
@Aspect
@Configuration
@ConditionalOnClass(DistributedLock.class)
@AutoConfigureAfter(DistributedLockAutoConfiguration.class)
public class DistributedLockAspectConfiguration {
	
	private final Logger logger = LoggerFactory.getLogger(DistributedLockAspectConfiguration.class);
	
	@Autowired
	private DistributedLock distributedLock;

	@Pointcut("@annotation(com.msxf.lock.spring.boot.autoconfigure.annotations.RedisLock)")
	private void lockPoint(){
		
	}
	
//	@Around("lockPoint()")
//	public Object around(ProceedingJoinPoint pjp) throws Throwable{
//		Method method = ((MethodSignature) pjp.getSignature()).getMethod();
//		RedisLock redisLock = method.getAnnotation(RedisLock.class);
//		String key = redisLock.value();
//		if(StringUtils.isEmpty(key)){
//			Object[] args = pjp.getArgs();
//			key = Arrays.toString(args);
//		}
//		int retryTimes = redisLock.action().equals(LockFailAction.CONTINUE) ? redisLock.retryTimes() : 1;
//		boolean lock = distributedLock.lock(key, redisLock.keepMills(), retryTimes, redisLock.sleepMills());
//		Object obj = null;
//		if(lock){
//			//得到锁,执行方法，释放锁
//			logger.info("get lock success : " + key);
//			obj = pjp.proceed();
//			distributedLock.releaseLock(key);
//		} else{
//			logger.info("get lock failed : " + key);
//		}
//		return obj;
//	}
	
	@Around("lockPoint()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable{
		Method method = ((MethodSignature) pjp.getSignature()).getMethod();
		RedisLock redisLock = method.getAnnotation(RedisLock.class);
		String key = redisLock.value();
		if(StringUtils.isEmpty(key)){
			Object[] args = pjp.getArgs();
			key = Arrays.toString(args);
		}
		int retryTimes = redisLock.action().equals(LockFailAction.CONTINUE) ? redisLock.retryTimes() : 1;
		boolean lock = distributedLock.lock(key, redisLock.keepMills(), retryTimes, redisLock.sleepMills());
		Object obj = null;
		if(lock){
			//得到锁,执行方法，释放锁
			logger.info("get lock success : " + key);
			obj = pjp.proceed();
			distributedLock.releaseLock(key);
		} else{
			logger.info("get lock failed : " + key);
		}
		return obj;
	}
}
