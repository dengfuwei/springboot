/**  
 * Project Name:msxf-activity-web 
 * File Name:ISessionUser.java  
 * Package Name:com.msxf.activity.interceptors  
 * Date:2017年3月28日下午3:37:28 
 * Copyright (c)2015, 马上消费金融股份有限公司  All Rights Reserved.  
 */ 
package com.itopener.framework.interceptors;

import javax.servlet.http.HttpServletRequest;

/**  
 * @ClassName:ISessionUser <br/> 
 * @Description <br/>
 * @date 2017年3月28日下午3:37:28 <br/>
 * @author fuwei.deng
 * @version 
 * @since JDK 1.6  
 * @see 
 */
public interface ISessionUser {

	/**
	 * @Description: 是否登录
	 * @return userId,如果大于0表示已登录，等于0表示未登录，返回userId方便记录用户操作日志
	 * @author 邓夫伟
	 * @date 2015年10月24日 下午4:27:30
	 */
	public long isLogin(HttpServletRequest request);
	
	/**
	 * @Description: 是否有权限
	 * @return true:有权限；false：无权限
	 * @author 邓夫伟
	 * @date 2015年10月24日 下午4:28:42
	 */
	public boolean hasAuthority(HttpServletRequest request, String controller);
}
