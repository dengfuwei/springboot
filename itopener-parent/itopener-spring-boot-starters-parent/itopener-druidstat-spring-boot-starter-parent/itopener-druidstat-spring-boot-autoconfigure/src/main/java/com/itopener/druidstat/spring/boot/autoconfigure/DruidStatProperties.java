/**  
 * Project Name:msxf-druidstat-spring-boot-autoconfigure 
 * File Name:DruidStatProperties.java  
 * Package Name:com.msxf.druidstat.spring.boot.autoconfigure  
 * Date:2017年4月6日下午5:36:33 
 * Copyright (c)2015, 马上消费金融股份有限公司  All Rights Reserved.  
 */ 
package com.itopener.druidstat.spring.boot.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**  
 * @ClassName:DruidStatProperties <br/> 
 * @Description <br/>
 * @date 2017年4月6日下午5:36:33 <br/>
 * @author fuwei.deng
 * @version 
 * @since JDK 1.6  
 * @see 
 */
@ConfigurationProperties(prefix="spring.druid.stat")
public class DruidStatProperties {

	private String url = "/druid/*";
	
	private String allow;
	
	private String deny;
	
	private String username;
	
	private String password;
	
	private String reset = "true";
	
	private boolean profileEnable = true;
	
	private String exclusions = "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*";
	
	private String filterName = "druidWebStatFilter";

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAllow() {
		return allow;
	}

	public void setAllow(String allow) {
		this.allow = allow;
	}

	public String getDeny() {
		return deny;
	}

	public void setDeny(String deny) {
		this.deny = deny;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getReset() {
		return reset;
	}

	public void setReset(String reset) {
		this.reset = reset;
	}

	public boolean isProfileEnable() {
		return profileEnable;
	}

	public void setProfileEnable(boolean profileEnable) {
		this.profileEnable = profileEnable;
	}

	public String getExclusions() {
		return exclusions;
	}

	public void setExclusions(String exclusions) {
		this.exclusions = exclusions;
	}

	public String getFilterName() {
		return filterName;
	}

	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}

}
