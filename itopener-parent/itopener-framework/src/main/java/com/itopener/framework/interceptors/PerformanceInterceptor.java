/**  
 * Project Name:msxf-activity-web 
 * File Name:PerformanceInterceptor.java  
 * Package Name:com.msxf.activity  
 * Date:2017年3月28日下午3:35:08 
 * Copyright (c)2015, 马上消费金融股份有限公司  All Rights Reserved.  
 */ 
package com.itopener.framework.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**  
 * @ClassName:PerformanceInterceptor <br/> 
 * @Description <br/>
 * @date 2017年3月28日下午3:35:08 <br/>
 * @author fuwei.deng
 * @version 
 * @since JDK 1.6  
 * @see 
 */
public class PerformanceInterceptor extends HandlerInterceptorAdapter {

	private final Logger log = LoggerFactory.getLogger(PerformanceInterceptor.class);
	
	private final String key = "timekey";
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		request.setAttribute(key, System.currentTimeMillis());
		request.setAttribute("basePath", "http://" + request.getServerName() + ":" + request.getServerPort() + "/");
		return super.preHandle(request, response, handler);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		log.info(request.getRequestURI() + " handle time millis : " + (System.currentTimeMillis() - (long) request.getAttribute(key)));
		super.afterCompletion(request, response, handler, ex);
	}
}
