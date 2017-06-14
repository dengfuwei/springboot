package com.itopener.redislock.spring.boot.autoconfigure.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author fuwei.deng
 * @date 2017年6月14日 下午3:10:36
 * @version 1.0.0
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface RedisLock {

	/**
     * redis的key
     * @return
     */
	String value() default "";
	
	/**
     * 持锁时间,单位毫秒,默认一分钟
     */
	long keepMills() default 30000;
	
	/**
     * 当获取失败时候动作
     */
	LockFailAction action() default LockFailAction.CONTINUE;
	
	public enum LockFailAction{
        /** 放弃 */
        GIVEUP,
        /** 继续 */
        CONTINUE;
    }
	
	/**
     * 睡眠时间,设置GIVEUP忽略此项
     * @return
     */
    long sleepMills() default 500;
    
    /** 重试次数*/
    int retryTimes() default Integer.MAX_VALUE;
}
