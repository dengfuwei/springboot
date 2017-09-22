package com.itopener.authority.config;

import javax.servlet.http.HttpServletRequest;

import com.itopener.framework.interceptors.AuthorityRequired;
import com.itopener.framework.interceptors.SessionUserAdapter;

public class SessionUser extends SessionUserAdapter {

	@Override
	public long isLogin(HttpServletRequest request) {
		SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute(AuthorityConstant.SESSION_INFO_KEY);
		return sessionInfo == null ? 0L : sessionInfo.getUserId();
	}

	@Override
	public boolean hasAuthority(HttpServletRequest request, AuthorityRequired authorityRequired, String controller) {
		byte[] roles = authorityRequired.role();
		SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute(AuthorityConstant.SESSION_INFO_KEY);
		if(sessionInfo == null){
			return false;
		}
		for(byte role : roles){
			if(role == sessionInfo.getRole()){
				return true;
			}
		}
		return false;
	}

}
