/**  
 * Project Name:msxf-activity-web 
 * File Name:LoginRequiredInterceptor.java  
 * Package Name:com.msxf.activity.interceptors  
 * Date:2017年3月28日下午3:36:42 
 * Copyright (c)2015, 马上消费金融股份有限公司  All Rights Reserved.  
 */ 
package com.itopener.framework.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;

/**  
 * @ClassName:LoginRequiredInterceptor <br/> 
 * @Description <br/>
 * @date 2017年3月28日下午3:36:42 <br/>
 * @author fuwei.deng
 * @version 
 * @since JDK 1.6  
 * @see 
 */
public class LoginRequiredInterceptor extends HandlerInterceptorAdapter {

private final Logger log = LoggerFactory.getLogger(LoginRequiredInterceptor.class);
	
	private ISessionUser sessionUser;
	
	/** 登录页面*/
	private String loginViewName;

	public ISessionUser getSessionUser() {
		return sessionUser;
	}

	public void setSessionUser(ISessionUser sessionUser) {
		this.sessionUser = sessionUser;
	}

	public String getLoginViewName() {
		return loginViewName;
	}

	public void setLoginViewName(String loginViewName) {
		this.loginViewName = loginViewName;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (!(handler instanceof HandlerMethod)) {
			return true;
		}
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		LoginRequired loginRequired = handlerMethod.getMethodAnnotation(LoginRequired.class);
		if(loginRequired == null){
			return true;
		}
		
		long userId = sessionUser.isLogin(request);
		//记录访问用户ID、访问地址、提交参数

		log.info(userId + "===" + request.getRequestURI() + "===" + JSON.toJSONString(request.getParameterMap()));
		if(userId == 0){
//			throw new BaseRuntimeException(FrameworkConstants.EXCEPTION_CODE_1001, "当前用户未登录或登录超时");
		}
		return super.preHandle(request, response, handler);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}
}
