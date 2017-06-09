/**  
 * Project Name:msxf-lock-spring-boot-autoconfigure 
 * File Name:DistributedLock.java  
 * Package Name:com.msxf.lock.spring.boot.autoconfigure  
 * Date:2017年4月14日上午9:59:48 
 * Copyright (c)2015, 马上消费金融股份有限公司  All Rights Reserved.  
 */ 
package com.itopener.zklock.spring.boot.autoconfigure.lock;

/**  
 * @ClassName:DistributedLock <br/> 
 * @Description <br/>
 * @date 2017年4月14日上午9:59:48 <br/>
 * @author fuwei.deng
 * @version 
 * @since JDK 1.6  
 * @see 
 */
public interface DistributedLock {
	
	public static final long TIMEOUT_SECOND = 30;
	
	public boolean lock(String key);
	
	public boolean lock(String key, long expire);
	
	public boolean releaseLock(String key);
}
