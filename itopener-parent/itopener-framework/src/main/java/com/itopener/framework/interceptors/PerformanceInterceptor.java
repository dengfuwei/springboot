package com.itopener.framework.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**  
 * @author fuwei.deng
 * @Date 2017年6月9日 下午3:10:58
 * @version 1.0.0
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
