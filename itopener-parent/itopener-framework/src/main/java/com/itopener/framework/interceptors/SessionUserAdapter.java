package com.itopener.framework.interceptors;

import javax.servlet.http.HttpServletRequest;

/**  
 * @author fuwei.deng
 * @Date 2017年6月9日 下午3:10:58
 * @version 1.0.0
 */
public class SessionUserAdapter implements ISessionUser {
	
//	private final Logger logger = LoggerFactory.getLogger(SessionUserAdapter.class);

	@Override
	public long isLogin(HttpServletRequest request) {
		return 0;
	}

	@Override
	public boolean hasAuthority(HttpServletRequest request, AuthorityRequired authorityRequired, String controller) {
		return false;
	}

}
