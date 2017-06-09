/**  
 * Project Name:msxf-tbschedule-spring-boot-autoconfigure 
 * File Name:TbScheduleProperties.java  
 * Package Name:com.msxf.tbschedule.spring.boot.autoconfigure  
 * Date:2017年4月1日下午5:52:32 
 * Copyright (c)2015, 马上消费金融股份有限公司  All Rights Reserved.  
 */ 
package com.itopener.tbschedule.spring.boot.autoconfigure;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;


/**  
 * @ClassName:TbScheduleProperties <br/> 
 * @Description <br/>
 * @date 2017年4月1日下午5:52:32 <br/>
 * @author fuwei.deng
 * @version 
 * @since JDK 1.6  
 * @see 
 */
@ConfigurationProperties(prefix="spring.tbschedule")
public class TbScheduleProperties {

	private Map<String, String> zkConfig;
	
	public Map<String, String> getZkConfig() {
		return zkConfig;
	}

	public void setZkConfig(Map<String, String> zkConfig) {
		this.zkConfig = zkConfig;
	}
}
