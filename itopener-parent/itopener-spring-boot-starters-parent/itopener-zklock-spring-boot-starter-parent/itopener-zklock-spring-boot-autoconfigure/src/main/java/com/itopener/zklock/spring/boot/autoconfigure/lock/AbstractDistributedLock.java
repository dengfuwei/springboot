/**  
 * Project Name:msxf-lock-spring-boot-autoconfigure 
 * File Name:AbstractDistributedLock.java  
 * Package Name:com.msxf.lock.spring.boot.autoconfigure.lock  
 * Date:2017年5月10日上午9:43:40 
 * Copyright (c)2015, 马上消费金融股份有限公司  All Rights Reserved.  
 */ 
package com.itopener.zklock.spring.boot.autoconfigure.lock;

/**
 * Created by fuwei.deng on 2017年5月10日.
 */
public class AbstractDistributedLock implements DistributedLock {

	@Override
	public boolean lock(String key) {
		return lock(key, TIMEOUT_SECOND);
	}

	@Override
	public boolean lock(String key, long expire) {
		return false;
	}

	@Override
	public boolean releaseLock(String key) {
		return false;
	}

}
