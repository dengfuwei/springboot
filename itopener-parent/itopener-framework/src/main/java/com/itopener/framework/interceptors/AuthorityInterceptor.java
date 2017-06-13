package com.itopener.framework.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;
import com.itopener.framework.FrameworkConstants;
import com.itopener.framework.base.BaseRuntimeException;

public class AuthorityInterceptor extends HandlerInterceptorAdapter {
	
	private final Logger logger = LoggerFactory.getLogger(AuthorityInterceptor.class);

	private ISessionUser sessionUser;
	
	/** 没有权限跳转页面*/
	private String noAuthViewName;

	public ISessionUser getSessionUser() {
		return sessionUser;
	}

	public AuthorityInterceptor setSessionUser(ISessionUser sessionUser) {
		this.sessionUser = sessionUser;
		return this;
	}

	public String getNoAuthViewName() {
		return noAuthViewName;
	}

	public AuthorityInterceptor setNoAuthViewName(String noAuthViewName) {
		this.noAuthViewName = noAuthViewName;
		return this;
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (!(handler instanceof HandlerMethod)) {
			return true;
		}
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		AuthorityRequired authorityRequired = handlerMethod.getMethodAnnotation(AuthorityRequired.class);
		if(authorityRequired == null){
			return true;
		}
		
		String controller = handlerMethod.getBean().getClass().getSimpleName();
		if(!sessionUser.hasAuthority(request, authorityRequired, controller)){
			logger.error(new StringBuilder("用户无访问权限===").append("===").append(request.getRequestURI()).append("===").append(JSON.toJSONString(request.getParameterMap())).toString());
			throw new BaseRuntimeException(FrameworkConstants.EXCEPTION_CODE_2001, "对不起，您暂时没有操作权限");
		}
		return super.preHandle(request, response, handler);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		super.afterCompletion(request, response, handler, ex);
	}
}
