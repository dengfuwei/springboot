package com.itopener.framework.interceptors;

import javax.servlet.http.HttpServletRequest;

/**  
 * @author fuwei.deng
 * @Date 2017年6月9日 下午3:10:58
 * @version 1.0.0
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
