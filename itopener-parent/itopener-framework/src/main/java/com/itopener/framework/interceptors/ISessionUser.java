package com.itopener.framework.interceptors;

import javax.servlet.http.HttpServletRequest;

/**  
 * @author fuwei.deng
 * @Date 2017年6月9日 下午3:10:58
 * @version 1.0.0
 */
public interface ISessionUser {

	/**
	 * @description 是否登录
	 * @author fuwei.deng
	 * @date 2017年6月13日 下午5:30:21
	 * @version 1.0.0
	 * @param request
	 * @return userId,如果大于0表示已登录，等于0表示未登录，返回userId方便记录用户操作日志
	 */
	public long isLogin(HttpServletRequest request);
	
	/**
	 * @description 是否有权限
	 * @author fuwei.deng
	 * @date 2017年6月13日 下午5:30:58
	 * @version 1.0.0
	 * @param request
	 * @param authorityRequired
	 * @param controller
	 * @return true:有权限；false：无权限
	 */
	public boolean hasAuthority(HttpServletRequest request, AuthorityRequired authorityRequired, String controller);
}
