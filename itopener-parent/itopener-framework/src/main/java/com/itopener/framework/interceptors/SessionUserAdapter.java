/**  
 * Project Name:msxf-activity-web 
 * File Name:SessionUserAdapter.java  
 * Package Name:com.msxf.activity.interceptors  
 * Date:2017年3月28日下午3:38:32 
 * Copyright (c)2015, 马上消费金融股份有限公司  All Rights Reserved.  
 */ 
package com.itopener.framework.interceptors;

import javax.servlet.http.HttpServletRequest;

/**  
 * @ClassName:SessionUserAdapter <br/> 
 * @Description <br/>
 * @date 2017年3月28日下午3:38:32 <br/>
 * @author fuwei.deng
 * @version 
 * @since JDK 1.6  
 * @see 
 */
public class SessionUserAdapter implements ISessionUser {
	
//	private final Logger logger = LoggerFactory.getLogger(SessionUserAdapter.class);

	@Override
	public long isLogin(HttpServletRequest request) {
		return 0;
	}

	@Override
	public boolean hasAuthority(HttpServletRequest request, String controller) {
		return false;
	}

}
